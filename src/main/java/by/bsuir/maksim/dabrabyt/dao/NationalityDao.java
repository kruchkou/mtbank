package by.bsuir.maksim.dabrabyt.dao;

import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Nationality;

import java.util.List;

public interface NationalityDao {

    //Метод для получения всех национальностей
    List<Nationality> getAll() throws DaoException;

}
