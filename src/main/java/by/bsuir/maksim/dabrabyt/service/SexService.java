package by.bsuir.maksim.dabrabyt.service;

import by.bsuir.maksim.dabrabyt.dao.model.entity.Sex;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;

import java.util.List;

public interface SexService {

    //Метод для получения всех полов
    List<Sex> getAll() throws ServiceException;

}
