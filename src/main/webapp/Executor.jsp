<%@ page contentType="text/html;charset=UTF-8" language="java" import ="
                                                    org.catalog.model.Executor,
                                                    java.util.List
"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <title>Исполнители</title>
        <script src="https://code.jquery.com/jquery-3.6.3.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

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
 <h1> Исполнители </h1>
<% List<Executor> executors = (List<Executor>)request.getAttribute("executors"); %>



<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Имя</th>
      <th scope="col"></th>
        <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  <%
      for(Executor e : executors){
  %>
    <tr>
      <th scope="row"><%= e.getId() %></th>
      <td><%= e.getName() %></td>
      <td><form action="/executors/delete" method="post">
                          <input type="hidden" name="id"  id="id" value="<%= e.getId() %>"/>
                          <button type="submit" class="btn btn-danger">Удалить <%= e.getId() %></button>
                      </form></td>
      <td><form action="/executors/edit" method="get">
                  <input type="hidden" name="id" value="<%= e.getId() %>"/>
                  <button type="submit" class="btn btn-success">Редактировать</button>
              </form></td>
    </tr>
    <%
        }
    %>

  </tbody>
</table>

<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addExecutorModal">Добавить исполнителя</button>

<div class="modal fade" id="addExecutorModal" tabindex="-1" aria-labelledby="addExecutorModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addExecutorModalLabel">Добавить исполнителя</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
      </div>
      <div class="modal-body">
        <form id="addExecutorForm" method="POST">
          <div class="mb-3">
            <label for="name" class="form-label">Имя исполнителя</label>
            <input type="text" id="name" name="name" class="form-control" required>
          </div>
          <button type="submit" class="btn btn-primary" role="button">Добавить</button>
        </form>
      </div>
    </div>
  </div>
</div>


<script>
$(document).ready(function () {

    $('#addExecutorForm').on('submit', function (event) {
        event.preventDefault();

        var formData = $(this).serialize();

        $.ajax({
            url: '/executors/add',
            type: 'POST',
            data: formData,
            success: function(response) {
                if (response === 'OK') {

                    $('#addExecutorModal').modal('hide');


                    location.reload();
                } else {
                    alert('Ошибка при добавлении исполнителя.');
                }
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('Произошла ошибка при добавлении исполнителя.');
            }
        });
    });
});
</script>
</body>
</html>