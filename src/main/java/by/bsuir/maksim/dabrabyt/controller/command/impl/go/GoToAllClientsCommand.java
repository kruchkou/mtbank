package by.bsuir.maksim.dabrabyt.controller.command.impl.go;

import by.bsuir.maksim.dabrabyt.controller.command.Command;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Client;
import by.bsuir.maksim.dabrabyt.service.ClientService;
import by.bsuir.maksim.dabrabyt.service.ServiceProvider;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class GoToAllClientsCommand implements Command {

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private static final ClientService clientService = ServiceProvider.getInstance().getClientService();

    //Метод, реализующий команду перехода на страницу всех клиентов
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, ServletException, IOException {

        List<Client> clientList = clientService.getAll();

        req.setAttribute("clientList", clientList);
        req.getRequestDispatcher("WEB-INF/clients.jsp").forward(req, resp);
    }
}
