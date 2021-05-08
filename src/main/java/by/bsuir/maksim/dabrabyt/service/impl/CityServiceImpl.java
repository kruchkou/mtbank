package by.bsuir.maksim.dabrabyt.service.impl;

import by.bsuir.maksim.dabrabyt.dao.CityDao;
import by.bsuir.maksim.dabrabyt.dao.DaoProvider;
import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.City;
import by.bsuir.maksim.dabrabyt.service.CityService;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class CityServiceImpl implements CityService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final CityServiceImpl instance = new CityServiceImpl();

    /*Экземпляр объекта CityDaoImpl, который предоставляет методы для
    взаимодействия с данными городов */
    private static final CityDao cityDao = DaoProvider.getInstance().getCityDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех городов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private CityServiceImpl() {
    }

    //Метод для получения всех городов
    @Override
    public List<City> getAll() throws ServiceException {
        try {
            return cityDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}
