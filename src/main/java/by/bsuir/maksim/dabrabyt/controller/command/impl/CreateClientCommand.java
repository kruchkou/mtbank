package by.bsuir.maksim.dabrabyt.controller.command.impl;

import by.bsuir.maksim.dabrabyt.controller.command.Command;
import by.bsuir.maksim.dabrabyt.controller.util.RequestClientParser;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Client;
import by.bsuir.maksim.dabrabyt.service.ClientService;
import by.bsuir.maksim.dabrabyt.service.ServiceProvider;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class CreateClientCommand implements Command {

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private final ClientService clientService = ServiceProvider.getInstance().getClientService();

    //Метод, обрабатывающий запрос на создание пользователя
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        Client client = RequestClientParser.parse(req);
        clientService.create(client);

        resp.sendRedirect("Controller?command=go_to_all_clients_command");
    }
}
