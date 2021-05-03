package by.bsuir.maksim.dabrabyt.controller.command.impl.go;

import by.bsuir.maksim.dabrabyt.controller.command.Command;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Client;
import by.bsuir.maksim.dabrabyt.service.*;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;
import by.bsuir.maksim.dabrabyt.service.util.RegexpPropertyUtil;
import by.bsuir.maksim.dabrabyt.service.util.validator.DateValidatorValueProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public final class GoToUpdateClientCommand implements Command {

    //Экземпляр класса RegexpPropertyUtil, предоставляющий значение по ключу из файла regexp.properties
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private static final ClientService clientService = ServiceProvider.getInstance().getClientService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными городов */
    private static final CityService cityService = ServiceProvider.getInstance().getCityService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными типов инвалидности */
    private static final DisabilityService disabilityService = ServiceProvider.getInstance().getDisabilityService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными типов семейного положения */
    private static final FamilyStatusService familyStatusService = ServiceProvider.getInstance().getFamilyStatusService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными национальностей */
    private static final NationalityService nationalityService = ServiceProvider.getInstance().getNationalityService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными полов */
    private static final SexService sexService = ServiceProvider.getInstance().getSexService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        int id = Integer.parseInt(req.getParameter("id"));
        Client client = clientService.get(id);

        req.setAttribute("id", id);
        req.setAttribute("client", client);
        req.setAttribute("cityList", cityService.getAll());
        req.setAttribute("nationalityList", nationalityService.getAll());
        req.setAttribute("familyStatusList", familyStatusService.getAll());
        req.setAttribute("disabilityList", disabilityService.getAll());
        req.setAttribute("sexList", sexService.getAll());

        req.setAttribute("regexp_user_fio", regexpPropertyUtil.getProperty("regexp.user_fio"));
        req.setAttribute("regexp_phone_number", regexpPropertyUtil.getProperty("regexp.phone_number"));
        req.setAttribute("regexp_email", regexpPropertyUtil.getProperty("regexp.email"));
        req.setAttribute("regexp_address", regexpPropertyUtil.getProperty("regexp.address"));
        req.setAttribute("regexp_passport_series", regexpPropertyUtil.getProperty("regexp.passport.series"));
        req.setAttribute("regexp_passport_issued_by", regexpPropertyUtil.getProperty("regexp.passport.issued_by"));
        req.setAttribute("regexp_passport_identity_number", regexpPropertyUtil.getProperty("regexp.passport.identity_number"));
        req.setAttribute("regexp_passport_birthplace", regexpPropertyUtil.getProperty("regexp.passport.birthplace"));
        req.setAttribute("min_birthdate", DateValidatorValueProvider.getMinBirthdate());
        req.setAttribute("date_today", LocalDate.now());

        req.getRequestDispatcher("WEB-INF/client-update.jsp").forward(req, resp);
    }
}
