<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.catalog.model.*, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <title>Добавление исполнителя</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="b-example-divider"></div>
<div class="container">
    <header class="d-flex justify-content-center py-3">
      <ul class="nav nav-pills">
        <li class="nav-item"><a href="/" class="nav-link active" aria-current="page">Главная</a></li>
        <li class="nav-item"><a href="/executors" class="nav-link">Исполнители</a></li>
        <li class="nav-item"><a href="/albums" class="nav-link">Альбомы</a></li>
        <li class="nav-item"><a href="/compositions" class="nav-link">Композиции</a></li>

      </ul>
    </header>
  </div>
 <div class="b-example-divider"></div>
<h1> Редактирование исполнителя </h1>

<%
    Album a = (Album) session.getAttribute("album");
    List<Executor> executors = (List<Executor>) session.getAttribute("executors");
%>
<form action="/albums/edit" method="POST">
        <label for="name" class="form-label">Название альбома</label>
        <input type="text" id="name" name="name" class="form-control" value="<%= a.getName() %>" required>
        <input type="hidden" name="id"  id="id" value="<%= a.getId() %>"/>
        <label for="genre" class="form-label">Жанр</label>
        <input type="text" id="genre" name="genre" class="form-control" value="<%= a.getGenre() %>" required>


        <select class="form-select" id="executorId" name="executorId" aria-label="Пример выбора по умолчанию" required>
                  <option value="" selected>-</option>
                  <% for (Executor e : executors){%>
                  <option value="<%= e.getId() %>"><%= e.getName() %></option>
                  <% } %>
                </select>
        <button type="submit" class="btn btn-primary" role="button">Изменить</button>
    </form>


</body>
</html>