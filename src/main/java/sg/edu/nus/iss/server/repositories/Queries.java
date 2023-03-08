package sg.edu.nus.iss.server.repositories;

public class Queries {
    // Query to insert new member into database
    public static final String SQL_INSERT_INTO_MEMBERS = "INSERT INTO members (name, telegram, grade, date) VALUES (?,?,?,?)";
    // Query to update an existing member in database
    public static final String SQL_UPDATE_MEMBER_BY_TELEGRAM = "UPDATE members SET name = ?, grade = ?, date = ? WHERE telegram = ?";
    // Query to retrieve an existing member from database
    public static final String SQL_SELECT_MEMBER_BY_TELEGRAM = "SELECT * FROM members WHERE telegram = ?";
}
