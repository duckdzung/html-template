public class UserBusiness {
    
    private UserDAO userDAO;

    public UserBusiness() {
        userDAO = new UserDAO();
    }

    public boolean login(String userId, String password) {
        User user = userDAO.findUserById(userId);
        return user != null && user.getPassword().equals(password);
    }

    public void logout() {
        // Logic for logging out the user
    }

    public void addUser(User user) {
        userDAO.insertUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(String userId) {
        userDAO.deleteUser(userId);
    }

    public List<User> searchUsers(String name, String sex, Date birthdayFrom, Date birthdayTo) {
        return userDAO.searchUsers(name, sex, birthdayFrom, birthdayTo);
    }
}