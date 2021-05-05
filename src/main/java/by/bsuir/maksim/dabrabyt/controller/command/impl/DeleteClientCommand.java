package by.bsuir.maksim.dabrabyt.controller.command.impl;

import by.bsuir.maksim.dabrabyt.controller.command.Command;
import by.bsuir.maksim.dabrabyt.service.ClientService;
import by.bsuir.maksim.dabrabyt.service.ServiceProvider;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class DeleteClientCommand implements Command {

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private final ClientService clientService = ServiceProvider.getInstance().getClientService();

    //Метод, обрабатывающий запрос на удаление пользователя
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        int id = Integer.parseInt(req.getParameter("id"));
        clientService.delete(id);

        resp.sendRedirect("Controller?command=go_to_all_clients_command");
    }
}
