package by.bsuir.alexey.mtbank.service.impl;

import by.bsuir.alexey.mtbank.dao.DaoProvider;
import by.bsuir.alexey.mtbank.dao.FamilyStatusDao;
import by.bsuir.alexey.mtbank.dao.exception.DaoException;
import by.bsuir.alexey.mtbank.dao.model.entity.FamilyStatus;
import by.bsuir.alexey.mtbank.service.FamilyStatusService;
import by.bsuir.alexey.mtbank.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class FamilyStatusServiceImpl implements FamilyStatusService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final FamilyStatusServiceImpl instance = new FamilyStatusServiceImpl();

    /*Экземпляр объекта FamilyStatusDaoImpl, который предоставляет методы для
    взаимодействия с данными типов семейного положения */
    private static final FamilyStatusDao familyStatusDao = DaoProvider.getInstance().getFamilyStatusDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех типов семейного положения
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private FamilyStatusServiceImpl() {
    }

    //Метод для получения всех типов семейного положения
    @Override
    public List<FamilyStatus> getAll() throws ServiceException {
        try {
            return familyStatusDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}
