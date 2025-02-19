public class ValidationUtils {

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean isValidBirthday(String birthday) {
        String birthdayRegex = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        return birthday != null && birthday.matches(birthdayRegex);
    }

    public static boolean isBirthdayRangeValid(String birthdayFrom, String birthdayTo) {
        if (isValidBirthday(birthdayFrom) && isValidBirthday(birthdayTo)) {
            return birthdayFrom.compareTo(birthdayTo) < 0;
        }
        return false;
    }
}