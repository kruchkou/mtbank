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

public final class GoToClientCommand implements Command {

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private static final ClientService clientService = ServiceProvider.getInstance().getClientService();

    //Метод, реализующий команду перехода на страницу клиента по его ID
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Client client = clientService.get(id);

        req.setAttribute("client", client);
        req.getRequestDispatcher("WEB-INF/client-info.jsp").forward(req, resp);
    }
}
