package by.bsuir.alexey.mtbank.dao;

import by.bsuir.alexey.mtbank.dao.exception.DaoException;
import by.bsuir.alexey.mtbank.dao.model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    //Метод для создания клиента
    void create(Client client) throws DaoException;

    //Метод для обновления клиента
    Client update(Client client) throws DaoException;

    //Метод для удаления клиента
    void delete(int id) throws DaoException;

    //Метод для получения клиента по его ID
    Optional<Client> get(int id) throws DaoException;

    //Метод для получения всех клиентов
    List<Client> getAll() throws DaoException;

}
