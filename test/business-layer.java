package com.crud.business;

import com.crud.model.Customer;
import com.crud.dao.CustomerDao;
import com.crud.utils.ValidationUtils;
import com.crud.constants.MessageConstants;
import java.time.LocalDate;
import java.util.List;

public class CustomerBusiness {
    private final CustomerDao customerDao;
    private final ValidationUtils validationUtils;
    
    public CustomerBusiness() {
        this.customerDao = new CustomerDao();
        this.validationUtils = new ValidationUtils();
    }
    
    public List<Customer> searchCustomers(String name, Character sex, 
            LocalDate birthday, int page, int pageSize) {
        // Validate pagination parameters
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 10;
        
        return customerDao.findCustomers(name, sex, birthday, page, pageSize);
    }
    
    public int getTotalPages(String name, Character sex, LocalDate birthday, int pageSize) {
        int totalCustomers = customerDao.getTotalCustomers(name, sex, birthday);
        return (int) Math.ceil((double) totalCustomers / pageSize);
    }
    
    public void createCustomer(Customer customer) throws BusinessException {
        validateCustomer(customer);
        try {
            customerDao.insert(customer);
        } catch (Exception e) {
            throw new BusinessException(MessageConstants.ERROR_CUSTOMER_CREATE, e);
        }
    }
    
    public void updateCustomer(Customer customer) throws BusinessException {
        validateCustomer(customer);
        try {
            if (customerDao.update(customer) == 0) {
                throw new BusinessException(MessageConstants.ERROR_CUSTOMER_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new BusinessException(MessageConstants.ERROR_CUSTOMER_UPDATE, e);
        }
    }
    
    public void deleteCustomer(Long id) throws BusinessException {
        try {
            if (customerDao.delete(id) == 0) {
                throw new BusinessException(MessageConstants.ERROR_CUSTOMER_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new BusinessException(MessageConstants.ERROR_CUSTOMER_DELETE, e);
        }
    }
    
    private void validateCustomer(Customer customer) throws BusinessException {
        List<String> errors = validationUtils.validate(customer);
        if (!errors.isEmpty()) {
            throw new BusinessException(String.join(", ", errors));
        }
    }
}

package com.crud.business;

import com.crud.model.User;
import com.crud.dao.UserDao;
import com.crud.utils.PasswordUtils;
import java.util.Optional;

public class LoginBusiness {
    private final UserDao userDao;
    private final PasswordUtils passwordUtils;
    
    public LoginBusiness() {
        this.userDao = new UserDao();
        this.passwordUtils = new PasswordUtils();
    }
    
    public boolean authenticate(String username, String password) throws BusinessException {
        try {
            Optional<User> user = userDao.findByUsername(username);
            return user.isPresent() && 
                   passwordUtils.verifyPassword(password, user.get().getPassword());
        } catch (Exception e) {
            throw new BusinessException(MessageConstants.ERROR_LOGIN_FAILED, e);
        }
    }
}

package com.crud.business;

public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
