package by.bsuir.maksim.dabrabyt.service;

import by.bsuir.maksim.dabrabyt.dao.model.entity.Disability;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Nationality;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;

import java.util.List;

public interface NationalityService {

    //Метод для получения всех национальностей
    List<Nationality> getAll() throws ServiceException;

}
