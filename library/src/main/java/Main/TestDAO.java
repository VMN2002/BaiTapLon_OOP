package Main;

import DAO.UserDAO;
import Model.User;

import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User newUser = new User("U021", "Samuel Jackson", "samuel@example.com");
        boolean isAdded = userDAO.addUser(newUser);
        System.out.println(isAdded ? "User added successfully!" : "Failed to add user.");
        List<User> users = userDAO.getAllUsers();
        System.out.println("List of users:");
        for (User user : users) {
            System.out.println(user);
        }
        User foundUser = userDAO.getUserById("U003");
        System.out.println(foundUser != null ? "Found user: " + foundUser : "User not found.");
        boolean isDeleted = userDAO.deleteUser("U021");
        System.out.println(isDeleted ? "User deleted successfully!" : "Failed to delete user.");
    }
}