# app.py

from flask import Flask, render_template, request, redirect, url_for, session, flash
from flask_mysqldb import MySQL 
import MySQLdb.cursors

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

@app.route('/forgot_pw', methods=['GET', 'POST'])
def forgot_pw():
    if request.method == 'POST':
        id = request.form['id']
        cur = mysql.connection.cursor()
        result = cur.execute('SELECT pw FROM users WHERE id = %s', [id])
        if result > 0:
            user = cur.fetchone()
            if user is not None:
                user_pw = user['pw']
                flash('Your password is: ' + user_pw)
            else:
                flash('Unexpected error occurred.')
        else:
            flash('No account found with that ID.')
        cur.close()
    return render_template('forgot_pw.html')

@app.route('/forgot_id', methods=['GET', 'POST'])
def forgot_id():
    if request.method == 'POST':
        name = request.form['name']
        cur = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        result = cur.execute('SELECT id FROM users WHERE name = %s', [name])
        if result > 0:
            user_id = cur.fetchone()['id']
            flash('Your ID is: ' + user_id)
        else:
            flash('No account found with that name.')
        cur.close()
    return render_template('forgot_id.html')

@app.route('/board')
def board():
    cur = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
    cur.execute("SELECT * FROM board")  # 비밀글 포함 모든 게시글을 가져옴
    board = cur.fetchall()
    cur.close()
    return render_template('board.html', board=board)


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

@app.route('/reset_session', methods=['GET'])
def reset_session():
    session['verified'] = False  # 페이지 로드시마다 'verified' 키의 값을 False로 재설정
    return redirect(url_for('postpw_check', no=session['no']))  # 'postpw_check' 페이지로 리다이렉트

@app.route('/postpw_check/<no>', methods=['GET', 'POST'])
def postpw_check(no):
    if 'loggedin' not in session:  # 로그인하지 않은 사용자인 경우
        return redirect(url_for('home'))  # 로그인 페이지로 리다이렉트
    session['no'] = no  # 게시글 번호 세션에 저장
    if request.method == 'POST':
        user_password = request.form.get('postpassword')  # 사용자가 입력한 비밀번호
        if not user_password:  # 비밀번호를 입력하지 않은 경우
            user_password = -1
        else:
            user_password = int(user_password)  # 비밀번호를 정수로 변환

        cur = mysql.connection.cursor()
        cur.execute("SELECT postpw FROM board WHERE no = %s", (no,))
        result = cur.fetchone()
        cur.close()

        db_password = result[0]  # 데이터베이스에서 가져온 비밀번호

        if db_password == user_password:  # 비밀번호가 일치하는 경우
            session['verified'] = True  # 세션에 인증 정보 저장
            return redirect(url_for('secretpost', no=no))
        else:
            flash('Incorrect Post PW.') # error message
    return render_template('postpw_check.html', no=no)

@app.route('/secretpost/<no>', methods=['GET'])
def secretpost(no):
    cur = mysql.connection.cursor()
    cur.execute("SELECT * FROM board WHERE no = %s", (no,))
    post = cur.fetchone()
    cur.close()

    if session.get('verified') != True:  # 인증되지 않은 경우
        flash('Access denied.')  # 접근 거부 메시지를 표시
        return redirect(url_for('postpw_check', no=no))
    
    # 인증된 경우에만 'secretpost.html' 페이지를 렌더링
    return render_template('secretpost.html', post=post)


    
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
            return "Incorrect post PW"
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
    keyword = request.args.get('keyword', None)  # 검색어가 없을 경우 None 반환
    cur = mysql.connection.cursor()

    if keyword:  # 검색어가 있는 경우
        cur.execute("SELECT * FROM board WHERE title LIKE %s OR maintext LIKE %s", ('%'+keyword+'%', '%'+keyword+'%'))
    else:  # 검색어가 없는 경우
        cur.execute("SELECT * FROM board")

    board = cur.fetchall()
    cur.close()
    return render_template('board.html', board=board)


if __name__ == "__main__":
    app.run(debug=True)
