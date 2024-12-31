package DB;

import common.CredentialJDBC;

import java.sql.*;
public class JDBConnection {
    // register new user to the database
    // true - register success
    // false - register failure
    public static boolean register(String username, String password, String fullName){
        try{
            // first check if the username already exists in the database
            if(!checkUser(username)){
                // connect to the database
                Connection connection = DriverManager.getConnection(CredentialJDBC.DB_URL,
                        CredentialJDBC.DB_USERNAME, CredentialJDBC.DB_PASSWORD);

                // create insert query
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CredentialJDBC.DB_USERS_TABLE_NAME + "(user_name, password, full_name)" +
                                "VALUES(?, ?, ?)"
                );

                // insert parameters in the insert query
                insertUser.setString(1, username);
                insertUser.setString(2, password);
                insertUser.setString(3, fullName);

                // update db with new user
                insertUser.executeUpdate();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    // check if username already exists in the database
    // false - user doesn't exists
    // true - user exists in the database
    public static boolean checkUser(String username){
        try{
            Connection connection = DriverManager.getConnection(CredentialJDBC.DB_URL,
                    CredentialJDBC.DB_USERNAME, CredentialJDBC.DB_PASSWORD);

            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + CredentialJDBC.DB_USERS_TABLE_NAME +
                            " WHERE user_name = ?"
            );
            checkUserExists.setString(1, username);

            ResultSet resultSet = checkUserExists.executeQuery();

            // check to see if the result set is empty
            // if it is empty it means that there was no data row that contains the username
            // (i.e user does not exist)
            if(!resultSet.isBeforeFirst()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    // validate login credentials by checking to see if username/password pair exists in the database
    public static boolean validateLogin(String username, String password){
        try{
            Connection connection = DriverManager.getConnection(CredentialJDBC.DB_URL,
                    CredentialJDBC.DB_USERNAME, CredentialJDBC.DB_PASSWORD);

            // create select query
            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + CredentialJDBC.DB_USERS_TABLE_NAME +
                            " WHERE user_name = ? AND password = ?"
            );
            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public static String getFullName(String username) {
        String fullName = "";
        try (Connection connection = DriverManager.getConnection(CredentialJDBC.DB_URL, CredentialJDBC.DB_USERNAME, CredentialJDBC.DB_PASSWORD)) {
            String query = "SELECT full_name FROM " + CredentialJDBC.DB_USERS_TABLE_NAME + " WHERE user_name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        fullName = resultSet.getString("full_name");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullName;
    }

}
