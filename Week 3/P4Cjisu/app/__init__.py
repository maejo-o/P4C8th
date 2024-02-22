from flask import Flask, render_template, request, redirect, url_for
from flask_mysqldb import MySQL

def create_app():
    app = Flask(__name__)

    app.config['MYSQL_HOST'] = 'localhost'
    app.config['MYSQL_USER'] = 'user'
    app.config['MYSQL_PASSWORD'] = 'password'
    app.config['MYSQL_DB'] = 'my_db'

    mysql = MySQL(app)

    from . import views
    app.register_blueprint(views.bp)

    return app
