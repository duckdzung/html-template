// CustomerBusiness.java
public class CustomerBusiness {
    private CustomerDAO customerDAO;
    
    public CustomerBusiness() {
        this.customerDAO = new CustomerDAO();
    }

    public ValidationResult validateCustomer(CustomerForm form) {
        ValidationResult result = new ValidationResult();
        
        // Validate required fields
        if (StringUtils.isEmpty(form.getName())) {
            result.addError("name", "Name is required");
        }

        // Validate birthday
        if (!ValidationUtils.isValidBirthday(form.getBirthday())) {
            result.addError("birthday", Constants.ERROR_BIRTHDAY_FORMAT);
        }

        // Validate email
        if (!ValidationUtils.isValidEmail(form.getEmail())) {
            result.addError("email", Constants.ERROR_INVALID_EMAIL); 
        }

        return result;
    }

    public ValidationResult validateSearch(String birthdayFrom, String birthdayTo) {
        ValidationResult result = new ValidationResult();

        // Validate birthday range
        if (!ValidationUtils.isBirthdayRangeValid(birthdayFrom, birthdayTo)) {
            result.addError("birthdayRange", Constants.ERROR_BIRTHDAY_RANGE);
        }

        return result;
    }

    public void addCustomer(CustomerForm form) throws BusinessException {
        // Validate first
        ValidationResult validationResult = validateCustomer(form);
        if (validationResult.hasErrors()) {
            throw new BusinessException(validationResult.getErrors());
        }

        // Convert form to model and save
        Customer customer = new Customer();
        // Map form fields to customer
        customerDAO.addCustomer(customer);
    }

    public void updateCustomer(CustomerForm form) throws BusinessException {
        ValidationResult validationResult = validateCustomer(form);
        if (validationResult.hasErrors()) {
            throw new BusinessException(validationResult.getErrors());
        }

        Customer customer = new Customer();
        // Map form fields to customer
        customerDAO.updateCustomer(customer); 
    }

    public List<Customer> searchCustomers(String name, String sex, 
            String birthdayFrom, String birthdayTo) throws BusinessException {
        
        ValidationResult validationResult = validateSearch(birthdayFrom, birthdayTo);
        if (validationResult.hasErrors()) {
            throw new BusinessException(validationResult.getErrors());
        }

        // Convert string dates to Date objects
        Date fromDate = DateUtils.parseDate(birthdayFrom);
        Date toDate = DateUtils.parseDate(birthdayTo);

        return customerDAO.searchCustomers(name, sex, fromDate, toDate);
    }
}

// ValidationResult.java 
public class ValidationResult {
    private Map<String, String> errors = new HashMap<>();

    public void addError(String field, String message) {
        errors.put(field, message);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

// BusinessException.java
public class BusinessException extends Exception {
    private Map<String, String> errors;

    public BusinessException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}