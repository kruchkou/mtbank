package by.bsuir.alexey.mtbank.service;

import by.bsuir.alexey.mtbank.dao.model.entity.Disability;
import by.bsuir.alexey.mtbank.service.exception.ServiceException;

import java.util.List;

public interface DisabilityService {

    //Метод для получения всех типов инвалидности
    List<Disability> getAll() throws ServiceException;

}
