<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>

<head>
  <meta charset='UTF-8' />
  <title>ДАБРАБЫТ. Информация о клиенте</title>
  <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/title_logo.png" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
    integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/core.css">
</head>

<body>

  <div id="wrapper">
    <nav class="navbar navbar-light bg-light">
      <a class="navbar-brand" href="${pageContext.request.contextPath}"><img id="logo" src="${pageContext.request.contextPath}/img/logo.png"></a>
      <p>Нам 20 лет!</p>
    </nav>


    <div class="mt-4 container-fluid d-flex justify-content-center">
      <div id="content" class="col-10 pb-4 bg-light">
        <div id="content-header" class="my-3">
          <div class="d-flex justify-content-between align-items-center">
            <h4>ИНФОРМАЦИЯ О КЛИЕНТЕ</h4>
          </div>
        </div>
        <div id="content-body" class="container-fluid d-flex">
          <div class="col-6">
            <div class="input-break color-primary mb-2">
              <p class="ml-3">Личные данные</p>
            </div>
            <div class="form-group">
              <label>Фамилия:</label>
              <span>${client.surname}</span>
            </div>
            <div class="form-group">
              <label>Имя:</label>
              <span>${client.surname}</span>
            </div>
            <div class="form-group">
              <label>Отчество:</label>
              <span>${client.patronymic}</span>
            </div>
            <div class="form-group">
              <label>Дата рождения:</label>
              <span>${client.birthdate}</span>
            </div>
            <div class="my-2 d-flex">
              <label class="mb-0 mr-4">Пол:</label>
              <div class="form-check form-check-inline">
                <span>${client.sex.name}</span>
              </div>
            </div>
            <div class="form-group">
              <label>Телефон дом.</label>
              <span>${empty client.phoneHome ? 'не указано' : client.phoneHome}</span>
            </div>
            <div class="form-group">
              <label>Телефон моб.</label>
              <span>${empty client.phoneCell ? 'не указано' : client.phoneCell}</span>
            </div>
            <div class="form-group">
              <label>E-mail</label>
              <span>${empty client.email ? 'не указано' : client.email}</span>
            </div>
            <div class="form-group">
              <label>Город факт. проживания:</label>
              <span>${client.actCity.name}</span>
            </div>
            <div class="form-group">
              <label>Адрес факт. проживания:</label>
              <span>${client.actAddress}</span>
            </div>
            <div class="form-group">
              <label>Ежемесячный доход:</label>
              <span>${empty client.salary ? 'не указано' : client.salary}</span>
            </div>
          </div>
          <div class="col-6">
            <div class="input-break color-primary mb-2">
              <p>Паспортные данные</p>
            </div>
            <div class="form-group">
              <label>Серия паспорта:</label>
              <span>${client.passportSeries}</span>
            </div>
            <div class="form-group">
              <label>№ паспорта:</label>
              <span>${client.passportNumber}</span>
            </div>
            <div class="form-group">
              <label>Кем выдан:</label>
              <span>${client.passportIssuedBy}</span>
            </div>
            <div class="form-group">
              <label>Дата выдачи:</label>
              <span>${client.passportIssuedDate}</span>
            </div>
            <div class="form-group">
              <label>Идентификационный номер:</label>
              <span>${client.passportIdentityNumber}</span>
            </div>
            <div class="form-group">
              <label>Город прописки:</label>
              <span>${client.passportCity.name}</span>
            </div>
            <div class="form-group">
              <label>Гражданство:</label>
              <span>${client.nationality.name}</span>
            </div>
            <div class="form-group">
              <label>Место рождения:</label>
              <span>${client.passportBirthplace}</span>
            </div>
            <div class="form-group">
              <label>Семейное положение:</label>
              <span>${client.familyStatus.name}</span>
            </div>
            <div class="form-group">
              <label>Инвалидность:</label>
              <span>${client.disability.name}</span>
            </div>
            <div class="form-group">
              <label>Пенсиорнер:</label>
              <span>${client.retiree == true ? 'Да' : 'Нет'}</span>
            </div>
          </div>
        </div>
      </div>

      <nav id="footer" class="navbar navbar-light bg-light justify-content-center text-muted">
        <p>Дабрабыт. Все права защищены</p>
      </nav>

    </div>
  </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
      crossorigin="anonymous"></script>
</body>

</html>