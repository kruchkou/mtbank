package by.bsuir.alexey.mtbank.dao;

import by.bsuir.alexey.mtbank.dao.impl.*;
import lombok.Getter;

public class DaoProvider {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final DaoProvider instance = new DaoProvider();

    /*Экземпляр объекта ClientDaoImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    @Getter
    private final ClientDao clientDao = ClientDaoImpl.getInstance();

    /*Экземпляр объекта CityDAOImpl, который предоставляет методы для
    взаимодействия с данными городов */
    @Getter
    private final CityDao cityDao = CityDaoImpl.getInstance();

    /*Экземпляр объекта NationalityDaoImpl, который предоставляет методы для
    взаимодействия с данными национальностей */
    @Getter
    private final NationalityDao nationalityDao = NationalityDaoImpl.getInstance();

    /*Экземпляр объекта FamilyStatusDaoImpl, который предоставляет методы для
    взаимодействия с данными типов семейного положения */
    @Getter
    private final FamilyStatusDao familyStatusDao = FamilyStatusDaoImpl.getInstance();

    /*Экземпляр объекта DisabilityDaoImpl, который предоставляет методы для
    взаимодействия с данными типов инвалидности */
    @Getter
    private final DisabilityDao disabilityDao = DisabilityDaoImpl.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private DaoProvider() {
    }

}
