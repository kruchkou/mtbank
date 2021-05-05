package by.bsuir.maksim.dabrabyt.dao;

import by.bsuir.maksim.dabrabyt.dao.impl.*;
import lombok.Getter;

public class DaoProvider {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final DaoProvider instance = new DaoProvider();

    /*Экземпляр объекта ClientDaoImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    @Getter
    private final ClientDao clientDAO = ClientDaoImpl.getInstance();

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

    /*Экземпляр объекта DisabilityDaoImpl, который предоставляет методы для
    взаимодействия с данными полов */
    @Getter
    private final SexDao sexDao = SexDaoImpl.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private DaoProvider() {
    }

}
