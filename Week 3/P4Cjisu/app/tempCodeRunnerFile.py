        cur.execute("SELECT * FROM board WHERE title LIKE %s OR maintext LIKE %s", ('%'+keyword+'%', '%'+keyword+'%'))
