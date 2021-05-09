package by.bsuir.alexey.mtbank.dao;

import by.bsuir.alexey.mtbank.dao.exception.DaoException;
import by.bsuir.alexey.mtbank.dao.model.entity.Disability;

import java.util.List;

public interface DisabilityDao {

    //Метод для получения всех типов инвалидности
    List<Disability> getAll() throws DaoException;

}
