package by.bsuir.alexey.mtbank.service.impl;

import by.bsuir.alexey.mtbank.dao.DaoProvider;
import by.bsuir.alexey.mtbank.dao.NationalityDao;
import by.bsuir.alexey.mtbank.dao.exception.DaoException;
import by.bsuir.alexey.mtbank.dao.model.entity.Nationality;
import by.bsuir.alexey.mtbank.service.NationalityService;
import by.bsuir.alexey.mtbank.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class NationalityServiceImpl implements NationalityService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final NationalityServiceImpl instance = new NationalityServiceImpl();

    /*Экземпляр объекта NationalityDaoImpl, который предоставляет методы для
    взаимодействия с данными национальностей */
    private static final NationalityDao nationalityDao = DaoProvider.getInstance().getNationalityDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех национальностей
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private NationalityServiceImpl() {
    }

    //Метод для получения всех национальностей
    @Override
    public List<Nationality> getAll() throws ServiceException {
        try {
            return nationalityDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}
