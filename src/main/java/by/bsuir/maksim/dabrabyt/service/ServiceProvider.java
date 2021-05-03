package by.bsuir.maksim.dabrabyt.service;

import by.bsuir.maksim.dabrabyt.service.impl.*;
import lombok.Getter;

public class ServiceProvider {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final ServiceProvider instance = new ServiceProvider();

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    @Getter
    private final ClientService clientService = ClientServiceImpl.getInstance();

    /*Экземпляр объекта CityServiceImpl, который предоставляет методы для
    взаимодействия с данными городов */
    @Getter
    private final CityService cityService = CityServiceImpl.getInstance();

    /*Экземпляр объекта DisabilityServiceImpl, который предоставляет методы для
    взаимодействия с данными типов инвалидности */
    @Getter
    private final DisabilityService disabilityService = DisabilityServiceImpl.getInstance();

    /*Экземпляр объекта FamilyStatusServiceImpl, который предоставляет методы для
    взаимодействия с данными типов семейного положения */
    @Getter
    private final FamilyStatusService familyStatusService = FamilyStatusServiceImpl.getInstance();

    /*Экземпляр объекта NationalityServiceImpl, который предоставляет методы для
    взаимодействия с данными национальностей */
    @Getter
    private final NationalityService nationalityService = NationalityServiceImpl.getInstance();

    /*Экземпляр объекта SexServiceImpl, который предоставляет методы для
    взаимодействия с данными национальностей */
    @Getter
    private final SexService sexService = SexServiceImpl.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private ServiceProvider() {
    }

}
