<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'/>
    <title>ДАБРАБЫТ. Обновление информации клиента</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/title_logo.png" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/core.css">
</head>

<body>

<div id="wrapper">
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">
            <img id="logo" src="${pageContext.request.contextPath}/img/logo.png">
        </a>
        <p>Банк свежих решений</p>
    </nav>

    <div class="mt-4 container-fluid d-flex justify-content-center">
        <div id="content" class="col-10 pb-4 bg-light">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="update_client_command">
                <input type="hidden" name="id" value="${client.id}">
                <div id="content-header" class="my-3">
                    <div class="d-flex justify-content-between align-items-center">
                        <h4>ИНФОРМАЦИЯ О КЛИЕНТЕ</h4>
                    </div>
                </div>
                <div id="content-body" class="container-fluid d-flex">
                    <div class="col-12">
                        <div class="input-break color-primary mb-2">
                            <p class="ml-3">Личные данные</p>
                        </div>
                        <div class="form-group">
                            <label for="surname">Фамилия*</label>
                            <input type="text" class="form-control form-control-sm" name="surname" id="surname"
                                   required pattern="${regexp_user_fio}" value="${client.surname}">
                        </div>
                        <div class="form-group">
                            <label for="name">Имя*</label>
                            <input type="text" class="form-control form-control-sm" name="name" id="name"
                                   required pattern="${regexp_user_fio}" value="${client.name}">
                        </div>
                        <div class="form-group">
                            <label for="patronymic">Отчество*</label>
                            <input type="text" class="form-control form-control-sm" name="patronymic" id="patronymic"
                                   required pattern="${regexp_user_fio}" value="${client.patronymic}">
                        </div>
                        <div class="form-group">
                            <label for="birthdate">Дата рождения*</label>
                            <input type="date" class="form-control form-control-sm" name="birthdate" id="birthdate"
                                   required max="${min_birthdate}" value="${client.birthdate}">
                        </div>
                        <div class="my-2 d-flex">
                            <label class="mb-0 mr-4">Пол*</label>
                            <c:forEach items="${sexList}" var="sex">
                                <div class="form-check form-check-inline">
                                    <c:if test="${client.sex.id == sex.id}">
                                        <input class="form-check-input" type="radio" name="sex" id="sex_${sex.id}"
                                               value="${sex.id}" required checked>
                                    </c:if>
                                    <c:if test="${client.sex.id != sex.id}">
                                        <input class="form-check-input" type="radio" name="sex" id="sex_${sex.id}"
                                               value="${sex.id}" required>
                                    </c:if>
                                    <label class="form-check-label" for="sex_${sex.id}">${sex.name}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="form-group">
                            <label for="phone_home">Телефон дом.</label>
                            <input type="tel" class="form-control form-control-sm" name="phone_home" id="phone_home"
                                   pattern="${regexp_phone_number} value="${client.phoneHome}">
                        </div>
                        <div class="form-group">
                            <label for="phone_cell">Телефон моб.</label>
                            <input type="tel" class="form-control form-control-sm" name="phone_cell" id="phone_cell"
                                   pattern="${regexp_phone_number} value="${client.phoneCell}">
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail</label>
                            <input type="email" class="form-control form-control-sm" name="email" id="email"
                                   aria-describedby="emailHelp"
                                   pattern="${regexp_email} value="${client.email}">
                        </div>
                        <div class="form-group">
                            <label for="work_place">Место работы</label>
                            <input type="text" class="form-control form-control-sm" name="work_place" id="work_place"
                                   pattern="${regexp_work} value="${client.workPlace}">
                        </div>
                        <div class="form-group">
                            <label for="work_position">Должность</label>
                            <input type="text" class="form-control form-control-sm" name="work_position" id="work_position"
                                   pattern="${regexp_work} value="${client.workPosition}">
                        </div>
                        <div class="form-group">
                            <label for="salary">Ежемесячный доход</label>
                            <input type="number" class="form-control form-control-sm" name="salary" id="salary" min="1" value="${client.salary}">
                        </div>
                        <div class="form-group">
                            <label for="city">Город факт. проживания*</label>
                            <select class="form-control form-control-sm" name="city" id="city" required>
                                <c:forEach items="${cityList}" var="city">
                                    <c:if test="${client.city.id == city.id}">
                                        <option value="${city.id}" selected>${city.name}</option>
                                    </c:if>
                                    <c:if test="${client.city.id != city.id}">
                                        <option value="${city.id}">${city.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="act_address">Адрес факт. проживания*</label>
                            <input type="text" class="form-control form-control-sm" name="act_address" id="act_address"
                                   required pattern="${regexp_address}" value="${client.actAddress}">
                        </div>
                        <div class="input-break color-primary mb-2">
                            <p>Документы</p>
                        </div>
                        <div class="form-group">
                            <label for="passport_series">Серия паспорта*</label>
                            <input type="text" class="form-control form-control-sm" name="passport_series"
                                   id="passport_series" required pattern="${regexp_passport_series}" value="${client.passportSeries}">
                        </div>
                        <div class="form-group">
                            <label for="passport_number">№ паспорта*</label>
                            <input type="number" class="form-control form-control-sm" name="passport_number"
                                   id="passport_number" required min="1" value="${client.passportNumber}">
                        </div>
                        <div class="form-group">
                            <label for="passport_issued_by">Кем выдан*</label>
                            <input type="text" class="form-control form-control-sm" name="passport_issued_by"
                                   id="passport_issued_by" required pattern="${regexp_passport_issued_by}" value="${client.passportIssuedBy}">
                        </div>
                        <div class="form-group">
                            <label for="passport_issued_date">Дата выдачи*</label>
                            <input type="date" class="form-control form-control-sm" name="passport_issued_date"
                                   id="passport_issued_date" required max="${date_today}" value="${client.passportIssuedDate}">
                        </div>
                        <div class="form-group">
                            <label for="passport_identity_number">Идентификационный номер*</label>
                            <input type="text" class="form-control form-control-sm" name="passport_identity_number"
                                   id="passport_identity_number" required pattern="${regexp_passport_identity_number}" value="${client.passportIdentityNumber}">
                        </div>
                        <div class="form-group">
                            <label for="passport_address">Город прописки*</label>
                            <input type="text" class="form-control form-control-sm" name="passport_address"
                                   id="passport_address" required pattern="${regexp_address}" value="${client.passportAddress}">
                        </div>
                        <div class="form-group">
                            <label for="nationality">Гражданство*</label>
                            <select class="form-control form-control-sm" name="nationality" id="nationality" required>
                                <c:forEach items="${nationalityList}" var="nationality">
                                    <c:if test="${client.nationality.id == nationality.id}">
                                        <option value="${nationality.id}" selected>${nationality.name}</option>
                                    </c:if>
                                    <c:if test="${client.nationality.id != nationality.id}">
                                        <option value="${nationality.id}">${nationality.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="birth_place">Место рождения*</label>
                            <input type="text" class="form-control form-control-sm" name="birth_place" id="birth_place"
                                   required pattern="${regexp_passport_birthplace}" value="${client.passportBirthplace}">
                        </div>
                        <div class="form-group">
                            <label for="family_status">Семейное положение*</label>
                            <select class="form-control form-control-sm" name="family_status" id="family_status"
                                    required>
                                <c:forEach items="${familyStatusList}" var="familyStatus">
                                    <c:if test="${client.familyStatus.id == familyStatus.id}">
                                        <option value="${familyStatus.id}" selected>${familyStatus.name}</option>
                                    </c:if>
                                    <c:if test="${client.familyStatus.id != familyStatus.id}">
                                        <option value="${familyStatus.id}">${familyStatus.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="disability">Инвалидность*</label>
                            <select class="form-control form-control-sm" name="disability" id="disability" required>
                                <c:forEach items="${disabilityList}" var="disability">
                                    <c:if test="${client.disability.id == disability.id}">
                                        <option value="${disability.id}" selected>${disability.name}</option>
                                    </c:if>
                                    <c:if test="${client.disability.id != disability.id}">
                                        <option value="${disability.id}">${disability.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-check">
                            <c:if test="${client.retiree == true}">
                                <input type="checkbox" class="form-check-input" name="retiree" id="retiree" checked>
                            </c:if>
                            <c:if test="${client.retiree != true}">
                                <input type="checkbox" class="form-check-input" name="retiree" id="retiree">
                            </c:if>
                            <label class="form-check-label" for="retiree">Пенсиорнер*</label>
                        </div>
                    </div>

                </div>
                <div class="d-flex flex-column">
                    <hr/>
                    <small id="emailHelp" class="ml-5 form-text text-muted">Поля, отмеченные ' * ', обязательны для
                        заполнения.</small>
                    <button type="submit" class="btn-save-client color-primary align-self-center btn mt-3">Сохранить
                    </button>
                </div>
            </form>
        </div>

        <nav id="footer" class="navbar navbar-light bg-light justify-content-center text-muted">
            <p>МТБанк</p>
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