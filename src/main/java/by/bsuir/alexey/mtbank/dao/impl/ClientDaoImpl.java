package by.bsuir.alexey.mtbank.dao.impl;

import by.bsuir.alexey.mtbank.dao.ClientDao;
import by.bsuir.alexey.mtbank.dao.connection.ConnectionPool;
import by.bsuir.alexey.mtbank.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.alexey.mtbank.dao.exception.DaoException;
import by.bsuir.alexey.mtbank.dao.model.entity.*;
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
            "surname, name, patronymic, birthdate, phone_home, phone_cell, email, work_place, work_position, salary, " +
            "city, act_address, pass_series, pass_num, pass_issued_by, pass_issued_date, pass_identity_num, " +
            "pass_address, nationality, pass_birthplace, family_status, disability, retiree) VALUES (" +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //SQL Запрос на удаление клиента по его ID
    private static final String DELETE_SQL = "DELETE FROM Clients WHERE id = ?";

    //SQL Запрос на обновление клиента
    private static final String UPDATE_SQL = "UPDATE Clients SET surname = ?, name = ?, patronymic = ?, birthdate = ?, " +
            "phone_home = ?, phone_cell = ?, email = ?, work_place = ?, work_position = ?, salary = ?, city = ?, " +
            "act_address = ?, pass_series = ?, pass_num = ?, pass_issued_by = ?, pass_issued_date = ?, " +
            "pass_identity_num = ?, pass_address = ?, nationality = ?, pass_birthplace = ?, family_status = ?, " +
            "disability = ?, retiree = ? WHERE id = ?";

    //SQL Запрос на получение клиента по его ID
    private static final String GET_SQL = "SELECT Clients.id, surname, Clients.name, patronymic, birthdate, phone_home, " +
            "phone_cell, email, work_place, work_position, salary, city.id, city.name, act_address, pass_series, " +
            "pass_num, pass_issued_by, pass_issued_date, pass_identity_num, pass_address, nationality.id, nationality.name, " +
            "pass_birthplace, family_status.id, family_status.name, disability.id, disability.name, retiree FROM Clients " +
            "JOIN Cities city ON Clients.city = city.id " +
            "JOIN ClientDisabilities disability ON Clients.disability = disability.id " +
            "JOIN ClientFamilyStatuses family_status ON Clients.family_status = family_status.id " +
            "JOIN ClientNationalities nationality ON Clients.nationality = nationality.id " +
            "WHERE Clients.id = ?";

    //SQL Запрос на всех клиентов
    private static final String GET_ALL_SQL = "SELECT Clients.id, surname, Clients.name, patronymic, birthdate, phone_home, " +
            "phone_cell, email, work_place, work_position, salary, city.id, city.name, act_address, pass_series, " +
            "pass_num, pass_issued_by, pass_issued_date, pass_identity_num, pass_address, nationality.id, nationality.name, " +
            "pass_birthplace, family_status.id, family_status.name, disability.id, disability.name, retiree FROM Clients " +
            "JOIN Cities city ON Clients.city = city.id " +
            "JOIN ClientDisabilities disability ON Clients.disability = disability.id " +
            "JOIN ClientFamilyStatuses family_status ON Clients.family_status = family_status.id " +
            "JOIN ClientNationalities nationality ON Clients.nationality = nationality.id";

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
        setPsFieldWithNullCheck(ps, 5, client.getPhoneHome());
        setPsFieldWithNullCheck(ps, 6, client.getPhoneCell());
        setPsFieldWithNullCheck(ps, 7, client.getEmail());
        setPsFieldWithNullCheck(ps, 8, client.getWorkPlace());
        setPsFieldWithNullCheck(ps, 9, client.getWorkPosition());
        setPsFieldWithNullCheck(ps, 10, client.getSalary());
        ps.setInt(11, client.getCity().getId());
        ps.setString(12, client.getActAddress());
        ps.setString(13, client.getPassportSeries());
        ps.setInt(14, client.getPassportNumber());
        ps.setString(15, client.getPassportIssuedBy());
        ps.setDate(16, Date.valueOf(client.getPassportIssuedDate()));
        ps.setString(17, client.getPassportIdentityNumber());
        ps.setString(18, client.getPassportAddress());
        ps.setInt(19, client.getNationality().getId());
        ps.setString(20, client.getPassportBirthplace());
        ps.setInt(21, client.getFamilyStatus().getId());
        ps.setInt(22, client.getDisability().getId());
        ps.setBoolean(23, client.getRetiree());
    }

    //Метод для правильной обработки полей, которые могут быть пустыми
    private static void setPsFieldWithNullCheck(PreparedStatement ps, int id, Integer value) throws SQLException {
        if (value == null) {
            ps.setNull(id, Types.INTEGER);
        } else {
            ps.setInt(id, value);
        }
    }

    //Метод для правильной обработки полей, которые могут быть пустыми
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
                .phoneHome(rs.getString("phone_home"))
                .phoneCell((rs.getString("phone_cell")))
                .email(rs.getString("email"))
                .workPlace(rs.getString("work_place"))
                .workPosition(rs.getString("work_position"))
                .city(new City(rs.getInt("city.id"), (rs.getString("city.name"))))
                .actAddress(rs.getString("act_address"))
                .passportSeries(rs.getString("pass_series"))
                .passportNumber(rs.getInt("pass_num"))
                .passportIssuedBy(rs.getString("pass_issued_by"))
                .passportIssuedDate(rs.getDate("pass_issued_date").toLocalDate())
                .passportIdentityNumber(rs.getString("pass_identity_num"))
                .passportAddress(rs.getString("pass_address"))
                .nationality(new Nationality(rs.getInt("nationality.id"), (rs.getString("nationality.name"))))
                .passportBirthplace((rs.getString("pass_birthplace")))
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
