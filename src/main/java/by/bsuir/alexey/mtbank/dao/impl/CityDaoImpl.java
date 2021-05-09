package by.bsuir.alexey.mtbank.dao.impl;

import by.bsuir.alexey.mtbank.dao.CityDao;
import by.bsuir.alexey.mtbank.dao.connection.ConnectionPool;
import by.bsuir.alexey.mtbank.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.alexey.mtbank.dao.exception.DaoException;
import by.bsuir.alexey.mtbank.dao.model.entity.City;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl implements CityDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final CityDaoImpl instance = new CityDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на получение всех городов
    private static final String GET_ALL_SQL = "SELECT * FROM Cities";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех городов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private CityDaoImpl() {
    }

    //Метод для получения всех городов
    @Override
    public List<City> getAll() throws DaoException {
        List<City> cityList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cityList.add(buildCityFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return cityList;
    }

    /* Приватный метод для создания экземпляра города из объекта ResultSet,
    содержащий данные из базы данных */
    private static City buildCityFromResultSet(ResultSet rs) throws SQLException {
        return City.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }

}
