package by.bsuir.alexey.mtbank.service;

import by.bsuir.alexey.mtbank.dao.model.entity.FamilyStatus;
import by.bsuir.alexey.mtbank.service.exception.ServiceException;

import java.util.List;

public interface FamilyStatusService {

    //Метод для получения всех семейных статусов
    List<FamilyStatus> getAll() throws ServiceException;

}
