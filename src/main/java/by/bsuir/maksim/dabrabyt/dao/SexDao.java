package by.bsuir.maksim.dabrabyt.dao;

import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Sex;

import java.util.List;

public interface SexDao {

    //Метод для получения всех полов
    List<Sex> getAll() throws DaoException;

}
