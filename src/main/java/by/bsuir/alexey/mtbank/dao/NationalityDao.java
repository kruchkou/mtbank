package by.bsuir.alexey.mtbank.dao;

import by.bsuir.alexey.mtbank.dao.exception.DaoException;
import by.bsuir.alexey.mtbank.dao.model.entity.Nationality;

import java.util.List;

public interface NationalityDao {

    //Метод для получения всех национальностей
    List<Nationality> getAll() throws DaoException;

}
