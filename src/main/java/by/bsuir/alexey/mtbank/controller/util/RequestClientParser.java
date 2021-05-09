package by.bsuir.alexey.mtbank.controller.util;

import by.bsuir.alexey.mtbank.dao.model.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public final class RequestClientParser {

    //Метод для извлечение объекта клиента из данных, находящихся в объекте HttpServletRequest
    public static Client parse(HttpServletRequest req) {
        String phoneHomeParameter = req.getParameter("phone_home");
        String phoneCellParameter = req.getParameter("phone_cell");
        String emailParameter = req.getParameter("email");
        String workPlaceParameter = req.getParameter("work_place");
        String workPositionParameter = req.getParameter("work_position");
        String salaryParameter = req.getParameter("salary");
        Boolean retiree = req.getParameter("retiree") == null ? false : true;

        String phoneHome = phoneHomeParameter.isEmpty() ? null : phoneHomeParameter;
        String phoneCell = phoneCellParameter.isEmpty() ? null : phoneCellParameter;
        String email = emailParameter.isEmpty() ? null : emailParameter;
        String workPlace = workPlaceParameter.isEmpty() ? null : workPlaceParameter;
        String workPosition = workPositionParameter.isEmpty() ? null : workPositionParameter;
        Integer salary = salaryParameter.isEmpty() ? null : Integer.parseInt(salaryParameter);

        LocalDate birthDate = LocalDate.parse(req.getParameter("birthdate"));
        City city = City.builder().id(Integer.parseInt(req.getParameter("city"))).build();
        LocalDate passportIssuedDate = LocalDate.parse(req.getParameter("passport_issued_date"));
        Nationality nationality = Nationality.builder().id(Integer.parseInt(req.getParameter("nationality"))).build();
        FamilyStatus familyStatus = FamilyStatus.builder().id(Integer.parseInt(req.getParameter("family_status"))).build();
        Disability disability = Disability.builder().id(Integer.parseInt(req.getParameter("disability"))).build();

        return Client.builder()
                .surname(req.getParameter("surname"))
                .name(req.getParameter("name"))
                .patronymic(req.getParameter("patronymic"))
                .birthdate(birthDate)
                .phoneHome(phoneHome)
                .phoneCell(phoneCell)
                .email(email)
                .workPlace(workPlace)
                .workPosition(workPosition)
                .city(city)
                .actAddress(req.getParameter("act_address"))
                .salary(salary)
                .passportSeries(req.getParameter("passport_series"))
                .passportNumber(Integer.parseInt(req.getParameter("passport_number")))
                .passportIssuedBy(req.getParameter("passport_issued_by"))
                .passportIssuedDate(passportIssuedDate)
                .passportIdentityNumber(req.getParameter("passport_identity_number"))
                .passportAddress(req.getParameter("passport_address"))
                .nationality(nationality)
                .passportBirthplace(req.getParameter("birth_place"))
                .familyStatus(familyStatus)
                .disability(disability)
                .retiree(retiree)
                .build();
    }

}
