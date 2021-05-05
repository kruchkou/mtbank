package by.bsuir.maksim.dabrabyt.dao.impl;

import by.bsuir.maksim.dabrabyt.dao.DisabilityDao;
import by.bsuir.maksim.dabrabyt.dao.connection.ConnectionPool;
import by.bsuir.maksim.dabrabyt.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.Disability;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisabilityDaoImpl implements DisabilityDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final DisabilityDaoImpl instance = new DisabilityDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на получение всех типов инвалидности
    private static final String GET_ALL_SQL = "SELECT * FROM ClientDisabilities";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех типов инвалидности
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private DisabilityDaoImpl() {
    }

    //Метод для получения всех типов инвалидности
    @Override
    public List<Disability> getAll() throws DaoException {
        List<Disability> disabilityList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                disabilityList.add(buildDisabilityFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return disabilityList;
    }

    /* Приватный метод для создания экземпляра инвалидности из объекта ResultSet,
    содержащий данные из базы данных */
    private static Disability buildDisabilityFromResultSet(ResultSet rs) throws SQLException {
        return Disability.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}
