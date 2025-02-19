public class ValidationUtils {

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean isValidDate(String date) {
        String dateRegex = "^(\\d{4})-(\\d{2})-(\\d{2})$";
        return date != null && date.matches(dateRegex);
    }

    public static boolean isValidBirthday(String birthday) {
        // Implement birthday validation logic here
        return isValidDate(birthday);
    }

    public static boolean isBirthdayRangeValid(String birthdayFrom, String birthdayTo) {
        // Implement logic to compare dates here
        return isValidDate(birthdayFrom) && isValidDate(birthdayTo) && birthdayFrom.compareTo(birthdayTo) < 0;
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}