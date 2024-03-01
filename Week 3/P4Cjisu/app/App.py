# app.py

from flask import Flask, render_template, request, redirect, url_for, session
from flask_mysqldb import MySQL

# 아래는 첨부파일 업로드를 위한 모듈
from werkzeug.utils import secure_filename
import os

from flask import Blueprint, send_file, request, redirect, url_for, render_template

UPLOAD_FOLDER = os.path.join(os.getcwd(), 'upload')
ALLOWED_EXTENSIONS = set(['txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'])

app = Blueprint('fileupload', __name__, url_prefix='/fileupload')

def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1] in ALLOWED_EXTENSIONS


# 아래는 첨부파일 다운로드를 위한 모듈
from flask import send_from_directory

app = Flask(__name__)


# MySQL 설정
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'jisu3101'
app.config['MYSQL_DB'] = 'crudboard'

app.config['SECRET_KEY'] = 'jisu3101'

# 파일을 저장할 디렉토리 설정
UPLOAD_FOLDER = os.path.join(app.root_path, 'uploads')
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


mysql = MySQL(app)

@app.route("/", methods = ['GET', 'POST'])

def home():
    return render_template('home.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
    msg = ' '
    if request.method == 'POST':
        userid = request.form['id']
        password = request.form['pw']
        cur = mysql.connection.cursor()
        cur.execute('SELECT * FROM users WHERE id = %s AND pw = %s', (userid, password,))
        account = cur.fetchone()
        if account:
            session['loggedin'] = True
            session['id'] = userid
            session['name'] = account[0] # 사용자 이름 저장
            return redirect(url_for('board'))  # 로그인 성공시 board 로 이동
        else:
            msg = 'Incorrect ID or PW!'  # 로그인 실패시 메시지 출력
        cur.close()
    return render_template('home.html', msg=msg)

@app.route('/logout')
def logout():
    session.pop('loggedin', None)
    session.pop('id', None)
    return redirect(url_for('home'))

@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        name = request.form['name']
        userid = request.form['id']
        password = request.form['pw']
        email = request.form['email']
        cur = mysql.connection.cursor()
        cur.execute('INSERT INTO users VALUES (%s, %s, %s, %s)', (name, userid, password, email,))
        mysql.connection.commit()
        cur.close()
        return redirect(url_for('home'))  # 회원가입 성공시 로그인 페이지로 이동
    return render_template('register.html')

@app.route('/forgot', methods=['GET', 'POST'])
def forgot():
    msg = ' '
    if request.method == 'POST':
        email = request.form['email']
        cur = mysql.connection.cursor()
        cur.execute('SELECT * FROM users WHERE email = %s', [email])
        account = cur.fetchone()
        if account:
            msg = 'Your ID is ' + account[1] + ' and your password is ' + account[2]
        else:
            msg = 'No account found for this email!'
        cur.close()
        return render_template('forgot.html', msg=msg)
    return render_template('forgot.html')


@app.route('/board')
def board():
    cur = mysql.connection.cursor()
    cur.execute("SELECT * FROM board")  # 비밀글 포함 모든 게시글을 가져옴
    boards = cur.fetchall()
    cur.close()
    return render_template('board.html', boards=boards)


@app.route('/add', methods=['GET', 'POST'])
def add():
    if 'loggedin' not in session:  # 로그인하지 않은 사용자인 경우
        return redirect(url_for('home'))  # 로그인 페이지로 리다이렉트
    if request.method == 'POST':
        title = request.form['title']
        content = request.form['maintext']
        password = request.form.get('postpw')  # 비밀번호 가져오기
        file = request.files.get('filename')

        if not password:  # 비밀번호가 입력되지 않은 경우
            password = -1  # 비밀번호를 -1로 설정
        else:
            password = int(password)  # 비밀번호를 정수로 변환

        # 첨부파일 업로드
        # 파일업로드 가능하게 끔 디렉터리 존재하지않을 시 생성
        if not os.path.exists(UPLOAD_FOLDER):
            os.makedirs(UPLOAD_FOLDER)
        filename = None
        if file and allowed_file(file.filename):
            filename = file.filename
            filepathtosave = os.path.join(UPLOAD_FOLDER, filename)
            file.save(filepathtosave)

        cur = mysql.connection.cursor()
        cur.execute("INSERT INTO board (title, maintext, postpw, filename) VALUES (%s, %s, %s, %s)", (title, content, password, filename))  # 비밀번호 및 파일명 저장
        mysql.connection.commit()
        cur.close()
        return redirect(url_for('board'))

    return render_template('add.html')

@app.route('/postpw_check/<postpw>', methods=['POST'])
def postpw_check(postpw):
    password = request.form.get('postpw')

    cur = mysql.connection.cursor()
    cur.execute("SELECT postpw FROM board WHERE no = %s", (no,))
    result = cur.fetchone()
    cur.close()

    if result[0] == password:  # 비밀번호가 일치하는 경우
        session['verified'] = True  # 세션에 인증 정보 저장
        return redirect(url_for('board'))  # 게시판 페이지로 리다이렉트
    else:
        session['verified'] = False  # 인증 실패 정보 저장
        return redirect(url_for('board'))  # 게시판 페이지로 리다이렉트

    
@app.route('/download/<filename>', methods = ['GET', 'POST'])
def download(filename):
    if 'loggedin' not in session:  # 로그인하지 않은 사용자인 경우
        return redirect(url_for('home'))  # 로그인 페이지로 리다이렉트

        cur = mysql.connection.cursor()
        cur.execute("SELECT postpw FROM board WHERE filename = %s", (filename,))
        result = cur.fetchone()
        cur.close()

        if request.method == 'POST':
            password = request.form.get('password')
        
            if result[0] != -1 and result[0] == password:  # 비밀번호가 설정된 게시글 + 비밀번호가 일치하는 경우
                try:
                    return send_from_directory(app.config['UPLOAD_FOLDER'], filename, as_attachment=True)
                except FileNotFoundError:
                    abort(404)
            else:
                return "Incorrect postpw"
        elif result[0] == -1:  # 비밀번호가 설정되지 않은 게시글인 경우
            try:
                return send_from_directory(app.config['UPLOAD_FOLDER'], filename, as_attachment=True)
            except FileNotFoundError:
                abort(404)
        else:
            return render_template("board.html", filename=filename)


@app.route('/update/<int:no>', methods=['GET', 'POST'])
def update(no):
    if 'loggedin' not in session:  # 로그인하지 않은 사용자인 경우
        return redirect(url_for('home'))  # 로그인 페이지로 리다이렉트
    cur = mysql.connection.cursor()
    if request.method == 'POST':
        title = request.form['title']
        content = request.form['maintext'] 
        cur.execute("UPDATE board SET title = %s, maintext = %s WHERE no = %s", (title, content, no))
        mysql.connection.commit()
        cur.close()
        return redirect(url_for('board'))
    else:
        cur.execute("SELECT * FROM board WHERE no = %s", [no])
        post = cur.fetchone()
        print(post)
        cur.close()
        if post is None:
            return "No post found"  # or redirect to 404 page
        return render_template('update.html', post=post)

@app.route('/delete/<int:no>', methods=['POST'])  
def delete(no):
    if 'loggedin' not in session:  # 로그인하지 않은 사용자인 경우
        return redirect(url_for('home'))  # 로그인 페이지로 리다이렉트
    cur = mysql.connection.cursor()
    cur.execute("DELETE FROM board WHERE no = %s", [no])
    mysql.connection.commit()
    cur.close()
    return redirect(url_for('board'))

@app.route('/search', methods=['GET'])
def search():
    keyword = request.args.get('keyword', '')
    cur = mysql.connection.cursor()
    cur.execute("SELECT * FROM board WHERE title LIKE %s OR maintext LIKE %s", ('%'+keyword+'%', '%'+keyword+'%'))
    board = cur.fetchall()
    cur.close()
    return render_template('board.html', board=board)

if __name__ == "__main__":
    app.run(debug=True)
