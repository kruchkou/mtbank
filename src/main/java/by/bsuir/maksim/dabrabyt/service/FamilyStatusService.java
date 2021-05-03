package by.bsuir.maksim.dabrabyt.service;

import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Client;
import by.bsuir.maksim.dabrabyt.dao.model.entity.FamilyStatus;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;

import java.util.List;

public interface FamilyStatusService {

    //Метод для получения всех семейных статусов
    List<FamilyStatus> getAll() throws ServiceException;

}
