package com.crud.constants;

public class DatabaseConstants {
    public static final String DB_URL = "db.url";
    public static final String DB_USERNAME = "db.username";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_DRIVER = "db.driver";
    public static final String DB_POOL_SIZE = "db.pool.size";
}

package com.crud.constants;

public class MessageConstants {
    // Success messages
    public static final String SUCCESS_CUSTOMER_CREATE = "message.customer.added";
    public static final String SUCCESS_CUSTOMER_UPDATE = "message.customer.updated";
    public static final String SUCCESS_CUSTOMER_DELETE = "message.customer.deleted";
    
    // Error messages
    public static final String ERROR_CUSTOMER_CREATE = "message.error.customer.create";
    public static final String ERROR_CUSTOMER_UPDATE = "message.error.customer.update";
    public static final String ERROR_CUSTOMER_DELETE = "message.error.customer.delete";
    public static final String ERROR_CUSTOMER_NOT_FOUND = "message.error.customer.not.found";
    public static final String ERROR_CUSTOMER_LIST = "message.error.customer.list";
    
    // Validation messages
    public static final String ERROR_NAME_REQUIRED = "message.validation.name.required";
    public static final String ERROR_INVALID_SEX = "message.validation.sex.invalid";
    public static final String ERROR_INVALID_BIRTHDAY = "message.validation.birthday.invalid";
    public static final String ERROR_INVALID_EMAIL = "message.validation.email.invalid";
    
    // Login messages
    public static final String ERROR_LOGIN_FAILED = "message.login.failed";
}

package com.crud.constants;

public class SqlConstants {
    public static final String CREATE_USER_TABLE = "sql.create.user.table";
    public static final String CREATE_CUSTOMER_TABLE = "sql.create.customer.table";
    public static final String INSERT_DEFAULT_USER = "sql.insert.default.user";
    public static final String SELECT_CUSTOMERS = "sql.select.customers";
    public static final String SELECT_CUSTOMERS_COUNT = "sql.select.customers.count";
    public static final String INSERT_CUSTOMER = "sql.insert.customer";
    public static final String UPDATE_CUSTOMER = "sql.update.customer";
    public static final String DELETE_CUSTOMER = "sql.delete.customer";
}
