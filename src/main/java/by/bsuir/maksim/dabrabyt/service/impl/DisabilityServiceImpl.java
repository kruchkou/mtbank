package by.bsuir.maksim.dabrabyt.service.impl;

import by.bsuir.maksim.dabrabyt.dao.DisabilityDao;
import by.bsuir.maksim.dabrabyt.dao.DaoProvider;
import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Disability;
import by.bsuir.maksim.dabrabyt.service.DisabilityService;
import by.bsuir.maksim.dabrabyt.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class DisabilityServiceImpl implements DisabilityService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final DisabilityServiceImpl instance = new DisabilityServiceImpl();

    /*Экземпляр объекта DisabilityDAOImpl, который предоставляет методы для
    взаимодействия с данными типов инвалидности */
    private static final DisabilityDao disabilityDao = DaoProvider.getInstance().getDisabilityDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех типов инвалидности
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private DisabilityServiceImpl() {
    }

    //Метод для получения всех типов инвалидности
    @Override
    public List<Disability> getAll() throws ServiceException {
        try {
            return disabilityDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}
