package by.bsuir.maksim.dabrabyt.service;

import by.bsuir.maksim.dabrabyt.dao.model.entity.City;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Nationality;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;

import java.util.List;

public interface CityService {

    //Метод для получения всех городов
    List<City> getAll() throws ServiceException;

}
