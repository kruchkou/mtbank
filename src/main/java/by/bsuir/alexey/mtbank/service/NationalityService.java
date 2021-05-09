package by.bsuir.alexey.mtbank.service;

import by.bsuir.alexey.mtbank.dao.model.entity.Nationality;
import by.bsuir.alexey.mtbank.service.exception.ServiceException;

import java.util.List;

public interface NationalityService {

    //Метод для получения всех национальностей
    List<Nationality> getAll() throws ServiceException;

}
