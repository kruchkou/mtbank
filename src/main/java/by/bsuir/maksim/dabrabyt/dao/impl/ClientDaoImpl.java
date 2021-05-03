package by.bsuir.maksim.dabrabyt.dao.impl;

import by.bsuir.maksim.dabrabyt.dao.ClientDao;
import by.bsuir.maksim.dabrabyt.dao.connection.ConnectionPool;
import by.bsuir.maksim.dabrabyt.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.maksim.dabrabyt.dao.exception.DaoException;
import by.bsuir.maksim.dabrabyt.dao.model.entity.*;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final ClientDaoImpl instance = new ClientDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на создание клиента
    private static final String CREATE_SQL = "INSERT INTO Clients(" +
            "surname, Clients.name, patronymic, birthdate, sex, phone_home, phone_cell, email, act_city, act_address, salary, " +
            "passport_series, passport_number, passport_issued_by, passport_issued_date, passport_identity_number, " +
            "passport_city, nationality, passport_birthplace, family_status, disability, retiree) VALUES (" +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //SQL Запрос на удаление клиента по его ID
    private static final String DELETE_SQL = "DELETE FROM Clients WHERE id = ?";

    //SQL Запрос на обновление клиента
    private static final String UPDATE_SQL = "UPDATE Clients SET surname = ?, name = ?, patronymic = ?, birthdate = ?, sex = ?," +
            " phone_home = ?, phone_cell = ?, email = ?, act_city = ?, act_address = ?, salary = ?, passport_series = ?, passport_number = ?," +
            " passport_issued_by = ?, passport_issued_date = ?, passport_identity_number = ?, passport_city = ?, nationality = ?, passport_birthplace = ?," +
            " family_status = ?, disability = ?, retiree = ? WHERE id = ?";

    //SQL Запрос на получение клиента по его ID
    private static final String GET_SQL = "SELECT Clients.id, surname, Clients.name, patronymic, birthdate, sex.id, sex.name, phone_home, " +
            "phone_cell, email, act_city.id, act_city.name, act_address, salary, passport_series, passport_number, " +
            "passport_issued_by, passport_issued_date, passport_identity_number, passport_city.id, passport_city.name, " +
            "nationality.id, nationality.name, passport_birthplace, family_status.id, family_status.name, " +
            "disability.id, disability.name, retiree FROM Clients " +
            "JOIN Cities act_city ON Clients.act_city = act_city.id " +
            "JOIN Cities passport_city ON Clients.passport_city = passport_city.id " +
            "JOIN ClientDisabilities disability ON Clients.disability = disability.id " +
            "JOIN ClientFamilyStatuses family_status ON Clients.family_status = family_status.id " +
            "JOIN ClientNationalities nationality ON Clients.nationality = nationality.id " +
            "JOIN ClientSex sex ON Clients.sex = sex.id " +
            "WHERE Clients.id = ?";

    //SQL Запрос на всех клиентов
    private static final String GET_ALL_SQL = "SELECT Clients.id, surname, Clients.name, patronymic, birthdate, sex.id, sex.name, phone_home, " +
            "phone_cell, email, act_city.id, act_city.name, act_address, salary, passport_series, passport_number, " +
            "passport_issued_by, passport_issued_date, passport_identity_number, passport_city.id, passport_city.name, " +
            "nationality.id, nationality.name, passport_birthplace, family_status.id, family_status.name, " +
            "disability.id, disability.name, retiree FROM Clients " +
            "JOIN Cities act_city ON Clients.act_city = act_city.id " +
            "JOIN Cities passport_city ON Clients.passport_city = passport_city.id " +
            "JOIN ClientDisabilities disability ON Clients.disability = disability.id " +
            "JOIN ClientFamilyStatuses family_status ON Clients.family_status = family_status.id " +
            "JOIN ClientNationalities nationality ON Clients.nationality = nationality.id " +
            "JOIN ClientSex sex ON Clients.sex = sex.id";

    //Сообщение, которое помещается в Exception в случае ошибки создания клиента
    private static final String MESSAGE_CREATE_EXCEPTION = "Create client failed";

    //Сообщение, которое помещается в Exception в случае ошибки удаления клиента
    private static final String MESSAGE_DELETE_EXCEPTION = "Create client failed";

    //Сообщение, которое помещается в Exception в случае ошибки обновления клиента
    private static final String MESSAGE_UPDATE_EXCEPTION = "Update client failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения клиента
    private static final String MESSAGE_GET_EXCEPTION = "Get client failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех клиентов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all clients failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private ClientDaoImpl() {
    }

    //Метод для создания клиента
    @Override
    public void create(Client client) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(CREATE_SQL);

            updatePreparedStatementFields(client, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_CREATE_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    //Метод для удаления клиента
    @Override
    public void delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(DELETE_SQL);

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_DELETE_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    /* Приватный метод для обновления объекта PreparedStatement,
    используемый для отправки данных в базу данных */
    private static void updatePreparedStatementFields(Client client, PreparedStatement ps) throws SQLException {
        ps.setString(1, client.getSurname());
        ps.setString(2, client.getName());
        ps.setString(3, client.getPatronymic());
        ps.setDate(4, Date.valueOf(client.getBirthdate()));
        ps.setInt(5, client.getSex().getId());
        setPsFieldWithNullCheck(ps, 6, client.getPhoneHome());
        setPsFieldWithNullCheck(ps, 7, client.getPhoneCell());
        ps.setString(8, client.getEmail());
        ps.setInt(9, client.getActCity().getId());
        ps.setString(10, client.getActAddress());
        setPsFieldWithNullCheck(ps, 11, client.getSalary());
        ps.setString(12, client.getPassportSeries());
        ps.setInt(13, client.getPassportNumber());
        ps.setString(14, client.getPassportIssuedBy());
        ps.setDate(15, Date.valueOf(client.getPassportIssuedDate()));
        ps.setString(16, client.getPassportIdentityNumber());
        ps.setInt(17, client.getPassportCity().getId());
        ps.setInt(18, client.getNationality().getId());
        ps.setString(19, client.getPassportBirthplace());
        ps.setInt(20, client.getFamilyStatus().getId());
        ps.setInt(21, client.getDisability().getId());
        ps.setBoolean(22, client.getRetiree());
    }

    private static void setPsFieldWithNullCheck(PreparedStatement ps, int id, Integer value) throws SQLException {
        if (value == null) {
            ps.setNull(id, Types.INTEGER);
        } else {
            ps.setInt(id, value);
        }
    }

    private static void setPsFieldWithNullCheck(PreparedStatement ps, int id, String value) throws SQLException {
        if (value == null) {
            ps.setNull(id, Types.VARCHAR);
        } else {
            ps.setString(id, value);
        }
    }

    //Метод для обновления клиента
    @Override
    public Client update(Client client) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(UPDATE_SQL);

            updatePreparedStatementFields(client, ps);

            Integer clientId = client.getId();
            ps.setInt(23, clientId);

            ps.executeUpdate();

            return get(clientId).get();
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_UPDATE_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    //Метод для получения клиента по его ID
    @Override
    public Optional<Client> get(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_SQL);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(buildClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return Optional.empty();
    }

    //Метод для получения всех клиентов
    @Override
    public List<Client> getAll() throws DaoException {
        List<Client> clientList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clientList.add(buildClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return clientList;
    }

    /* Приватный метод для создания экземпляра клиента из объекта ResultSet,
    содержащий данные из базы данных */
    private static Client buildClientFromResultSet(ResultSet rs) throws SQLException {
        Client client = Client.builder()
                .id(rs.getInt("Clients.id"))
                .surname(rs.getString("surname"))
                .name(rs.getString("Clients.name"))
                .patronymic(rs.getString("patronymic"))
                .birthdate(rs.getDate("birthdate").toLocalDate())
                .sex(new Sex(rs.getInt("sex.id"), rs.getString("sex.name")))
                .phoneHome(rs.getString("phone_home"))
                .phoneCell((rs.getString("phone_cell")))
                .email(rs.getString("email"))
                .actCity(new City(rs.getInt("act_city.id"), (rs.getString("act_city.name"))))
                .actAddress(rs.getString("act_address"))
                .passportSeries(rs.getString("passport_series"))
                .passportNumber(rs.getInt("passport_number"))
                .passportIssuedBy(rs.getString("passport_issued_by"))
                .passportIssuedDate(rs.getDate("passport_issued_date").toLocalDate())
                .passportIdentityNumber(rs.getString("passport_identity_number"))
                .passportCity(new City(rs.getInt("passport_city.id"), (rs.getString("passport_city.name"))))
                .nationality(new Nationality(rs.getInt("nationality.id"), (rs.getString("nationality.name"))))
                .passportBirthplace((rs.getString("passport_birthplace")))
                .familyStatus(new FamilyStatus(rs.getInt("family_status.id"), (rs.getString("family_status.name"))))
                .disability(new Disability(rs.getInt("disability.id"), (rs.getString("disability.name"))))
                .retiree(rs.getBoolean("retiree"))
                .build();

        Integer salary = rs.getInt("salary");
        if (!rs.wasNull()) {
            client.setSalary(salary);
        }

        return client;
    }
}
