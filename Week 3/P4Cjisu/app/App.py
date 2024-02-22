# app.py

from flask import Flask, render_template, request, redirect, url_for
from flask_mysqldb import MySQL

app = Flask(__name__)

# MySQL 설정
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'jisu3101'
app.config['MYSQL_DB'] = 'crudboard'

mysql = MySQL(app)

@app.route("/")
def index():
    cur = mysql.connection.cursor()
    cur.execute("SELECT * FROM board")
    board = cur.fetchall()
    cur.close()
    return render_template("board.html", board=board)

@app.route('/add', methods=['POST'])
def add():
    if request.method == 'POST':
        title = request.form['title']
        content = request.form['context']  # 'maintext' -> 'context'
        cur = mysql.connection.cursor()
        cur.execute("INSERT INTO board (title, maintext) VALUES (%s, %s)", (title, content))
        mysql.connection.commit()
        cur.close()
        return redirect(url_for('index'))

@app.route('/update/<int:no>', methods=['GET', 'POST'])  # 'uid' -> 'no'
def update(no):
    cur = mysql.connection.cursor()
    if request.method == 'POST':
        title = request.form['title']
        content = request.form['context']  # 'maintext' -> 'context'
        cur.execute("UPDATE board SET title = %s, maintext = %s WHERE no = %s", (title, content, no))
        mysql.connection.commit()
        return redirect(url_for('index'))
    else:
        cur.execute("SELECT * FROM board WHERE no = %s", [no])
        post = cur.fetchone()
        return render_template('update.html', post=post)

@app.route('/delete/<int:no>', methods=['POST'])  # 'uid' -> 'no'
def delete(no):
    cur = mysql.connection.cursor()
    cur.execute("DELETE FROM board WHERE no = %s", [no])
    mysql.connection.commit()
    cur.close()
    return redirect(url_for('index'))

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
