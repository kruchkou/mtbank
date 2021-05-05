package by.bsuir.maksim.dabrabyt.service;

import by.bsuir.maksim.dabrabyt.dao.model.entity.Disability;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;

import java.util.List;

public interface DisabilityService {

    //Метод для получения всех типов инвалидности
    List<Disability> getAll() throws ServiceException;

}
