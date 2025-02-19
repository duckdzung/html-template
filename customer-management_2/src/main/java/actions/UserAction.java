public class UserAction {

    private UserForm userForm;
    private UserBusiness userBusiness;

    public UserAction() {
        userBusiness = new UserBusiness();
        userForm = new UserForm();
    }

    public String login() {
        if (userBusiness.validateUser(userForm.getUserId(), userForm.getPassword())) {
            return "success"; // Redirect to success page
        } else {
            return "error"; // Redirect to error page
        }
    }

    public String logout() {
        // Logic for logging out the user
        return "logout"; // Redirect to logout page
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }
}