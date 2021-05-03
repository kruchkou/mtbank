package by.bsuir.maksim.dabrabyt.dao.impl;

import by.bsuir.maksim.dabrabyt.dao.FamilyStatusDao;
import by.bsuir.maksim.dabrabyt.dao.connection.ConnectionPool;
import by.bsuir.maksim.dabrabyt.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.FamilyStatus;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamilyStatusDaoImpl implements FamilyStatusDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final FamilyStatusDaoImpl instance = new FamilyStatusDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на получение всех типов семейных положений
    private static final String GET_ALL_SQL = "SELECT * FROM ClientFamilyStatuses";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех типов семейных положений
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private FamilyStatusDaoImpl() {
    }

    //Метод для получения всех типов семейных положений
    @Override
    public List<FamilyStatus> getAll() throws DaoException {
        List<FamilyStatus> familyStatusList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                familyStatusList.add(buildFamilyStatusFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return familyStatusList;
    }

    /* Приватный метод для создания экземпляра семейного положения из объекта ResultSet,
    содержащий данные из базы данных */
    private static FamilyStatus buildFamilyStatusFromResultSet(ResultSet rs) throws SQLException {
        return FamilyStatus.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}
