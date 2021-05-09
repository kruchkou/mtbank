package by.bsuir.alexey.mtbank.service;

import by.bsuir.alexey.mtbank.dao.model.entity.City;
import by.bsuir.alexey.mtbank.service.exception.ServiceException;

import java.util.List;

public interface CityService {

    //Метод для получения всех городов
    List<City> getAll() throws ServiceException;

}
