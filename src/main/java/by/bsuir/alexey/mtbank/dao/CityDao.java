package by.bsuir.alexey.mtbank.dao;

import by.bsuir.alexey.mtbank.dao.exception.DaoException;
import by.bsuir.alexey.mtbank.dao.model.entity.City;

import java.util.List;

public interface CityDao {

    //Метод для получения всех городов
    List<City> getAll() throws DaoException;

}
