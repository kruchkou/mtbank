package by.bsuir.maksim.dabrabyt.dao;

import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.City;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface CityDao {

    //Метод для получения всех городов
    List<City> getAll() throws DaoException;

}
