package by.bsuir.alexey.mtbank.dao;

import by.bsuir.alexey.mtbank.dao.exception.DaoException;
import by.bsuir.alexey.mtbank.dao.model.entity.FamilyStatus;

import java.util.List;

public interface FamilyStatusDao {

    //Метод для получения всех типов семейных положений
    List<FamilyStatus> getAll() throws DaoException;

}
