<%@ page contentType="text/html;charset=UTF-8" language="java" import ="
                                                    org.catalog.model.Executor,
                                                    java.util.List
"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <title>Добавление альбома</title>
        <script src="https://code.jquery.com/jquery-3.6.3.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<style>
#executor-container {
    position: relative;
}

.dropdown-menu {
    width: 200px; /* Ширина подсказок */
    position: absolute;
    top: 70px; /* Смещение вниз относительно поля ввода */
    left: 50%; /* Размещение справа от поля ввода */
    margin-left: 10px; /* Отступ между полем ввода и подсказками */
    background-color: white;
    border: 1px solid #ccc;
    padding: 5px;
    z-index: 9999; /* Чтобы подсказки находились поверх других элементов */
}

.dropdown-menu li {
    cursor: pointer;
    padding: 5px;
}

.dropdown-menu li:hover {
    background-color: #f0f0f0;
}
</style>
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
<h1> Добавление альбома </h1>

<form action="/albums/add" method="POST">
        <label for="name" class="form-label">Название альбома</label>
        <input type="text" id="name" name="name" class="form-control" required>

        <label for="genre" class="form-label">Жанр</label>
                <input type="text" id="genre" name="genre" class="form-control" required>

        <!--<% List<Executor> executors = (List<Executor>)request.getAttribute("executors"); %><label for="executorId" class="form-label">Исполнитель</label><select class="form-select" id="executorId" name="executorId" aria-label="Пример выбора по умолчанию" required><option value="" selected>-</option><% for (Executor e : executors){%><option value="<%= e.getId() %>"><%= e.getName() %></option><% }%></select>-->
        <!--<div id="executor-container"><label for="executorId" class="form-label">Исполнитель</label><select class="form-select" id="executorId" name="executorId" aria-label="Пример выбора по умолчанию" required><option value="" selected>-</option></select></div>-->

            <div id="executor-container">
                <label for="executorInput" class="form-label">Исполнитель</label>
                <input type="text" id="executorInput" name="executorName" class="form-control" autocomplete="off" required>
                <input type="hidden" id="executorId" name="executorId" value="" />
                <ul id="autocomplete-list" class="dropdown-menu" style="width: 40%; display: none;"></ul>
              </div>

        <button type="submit" class="btn btn-primary" role="button">Добавить</button>
    </form>

    <script>
        function validateSelection() {
            const select = document.getElementById('executorId');
            if (select.value === '') {
                alert('Выберите исполнителя.');
                return false;
            }
            return true;
        }
    </script>


<!--<script>$(document).ready(function () {$('#executorId').on('click', function() {if ($('#executorId option').length <= 1) {$.ajax({url: "/executorsajax",type: "GET",success: function(response) {let $select = $('#executorId');$select.empty().append('<option value="" selected>-</option>');response.forEach(executor => {$select.append(`<option value="${executor.id}">${executor.name}</option>`);});},error: function(xhr, status, error) {console.error("Ошибка при получении списка исполнителей:", xhr.status, xhr.statusText);}});}});});</script>-->
<script>
$(document).ready(function () {
    // Функция для обработки изменения значения в поле ввода
    $('#executorInput').on('input', function() {
        var inputVal = $(this).val(); // Получаем значение из поля ввода

        if (inputVal.length > 0) { // Если длина введённого текста больше нуля
            $.ajax({
                url: "/executorsajax",
                data: { query: inputVal }, // Передаем введённый текст на сервер
                type: "GET",
                success: function(response) {
                    updateAutocompleteList(response); // Обновляем список подсказок
                },
                error: function(xhr, status, error) {
                    console.error("Ошибка при получении списка исполнителей:", xhr.status, xhr.statusText);
                }
            });
        } else {
            hideAutocompleteList(); // Скрываем список подсказок, если ничего не введено
        }
    });

    // Функция для обновления списка подсказок
    function updateAutocompleteList(data) {
        var $list = $('#autocomplete-list');
        $list.empty(); // Очищаем текущий список

        data.forEach(executor => {
            $list.append(`<li data-executor-id="${executor.id}" onclick="setSelectedExecutor(${executor.id}, '${executor.name}')">${executor.name}</li>`);
        });

        showAutocompleteList(); // Показываем список подсказок
    }

    // Функция для скрытия списка подсказок
    function hideAutocompleteList() {
        $('#autocomplete-list').hide();
    }

    // Функция для отображения списка подсказок
    function showAutocompleteList() {
        $('#autocomplete-list').show();
    }


   function setSelectedExecutor(id, name) {
       $('#executorInput').val(name); // Устанавливаем выбранное имя исполнителя в поле ввода
       $('#executorId').val(id); // Сохраняем ID исполнителя в скрытом поле
   }
    $('#autocomplete-list').on('click', 'li', function() {
        const executorId = $(this).data('executor-id'); // Получаем ID исполнителя
        const executorName = $(this).text(); // Получаем имя исполнителя
        setSelectedExecutor(executorId, executorName);
        hideAutocompleteList();
    });


});

</script>

</body>
</html>
