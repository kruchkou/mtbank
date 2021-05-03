package by.bsuir.maksim.dabrabyt.dao;

import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Client;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Disability;

import java.util.List;

public interface DisabilityDao {

    //Метод для получения всех типов инвалидности
    List<Disability> getAll() throws DaoException;

}
