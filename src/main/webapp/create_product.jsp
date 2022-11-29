<html>
<head>
    <title>Admin panel</title>
</head>

<body>
<h1>Admin panel</h1>
<h2>CREATE</h2>

<form method="post" action="update">
    id: <input name="id"><br>
    name: <input name="name"><br>
    price: <input name="price"><br>
    <input type="submit">
</form>

<br><br>
<%--отправит на стандартную форму выхода--%>
<form method="get" action="/logout">
    <input type="submit" value="LOGOUT">
</form>

</body>
</html>