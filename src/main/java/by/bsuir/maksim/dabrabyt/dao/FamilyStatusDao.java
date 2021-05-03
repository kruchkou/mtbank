package by.bsuir.maksim.dabrabyt.dao;

import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Client;
import by.bsuir.maksim.dabrabyt.dao.model.entity.FamilyStatus;

import java.util.List;

public interface FamilyStatusDao {

    //Метод для получения всех типов семейных положений
    List<FamilyStatus> getAll() throws DaoException;

}
