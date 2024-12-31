<%@ page contentType="text/html;charset=UTF-8" language="java" import ="
                                                    org.catalog.model.Composition,
                                                    java.util.List
"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <title> Композиции </title>
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
<h1> Композиции </h1>

<% List<Composition> compositions = (List<Composition>)request.getAttribute("compositions"); %>



<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Название</th>
      <th scope="col">Длительность</th>
      <th scope="col">Альбом</th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  <%
      for(Composition c : compositions){
  %>
    <tr>
      <th scope="row"><%= c.getId() %></th>
      <td><%= c.getName() %></td>
      <td><%= c.getLength() %></td>
      <td><%= c.getAlbum().getName() %></td>
      <td><form action="/compositions/delete" method="post">
                          <input type="hidden" name="id"  id="id" value="<%= c.getId() %>"/>
                          <button type="submit" class="btn btn-danger">Удалить <%= c.getId() %></button>
                      </form></td>
      <td><form action="/compositions/edit" method="get">
                  <input type="hidden" name="id" value="<%= c.getId() %>"/>
                  <button type="submit" class="btn btn-success">Редактировать</button>
              </form></td>
    </tr>
    <%
        }
    %>

  </tbody>
</table>
<a class="btn btn-primary" href="/compositions/add" role="button">Добавить композицию</a>

</body>
</html>