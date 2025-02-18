package com.crud.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer {
    private Long id;
    private String name;
    private char sex;
    private LocalDate birthday;
    private String email;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public char getSex() { return sex; }
    public void setSex(char sex) { this.sex = sex; }
    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getFormattedBirthday() {
        return birthday != null ? birthday.format(DateTimeFormatter.ISO_DATE) : "";
    }
}

package com.crud.dao;

import com.crud.model.Customer;
import com.crud.constants.SqlConstants;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class CustomerDao extends BaseDao<Customer> {
    private final Properties sqlProps;
    
    public CustomerDao() {
        sqlProps = new Properties();
        try (var is = getClass().getClassLoader().getResourceAsStream("sql.properties")) {
            sqlProps.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SQL properties", e);
        }
    }
    
    private Customer mapCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setSex(rs.getString("sex").charAt(0));
        customer.setBirthday(rs.getDate("birthday").toLocalDate());
        customer.setEmail(rs.getString("email"));
        return customer;
    }
    
    public List<Customer> findCustomers(String name, Character sex, LocalDate birthday, int page, int pageSize) {
        StringBuilder sql = new StringBuilder(sqlProps.getProperty("sql.select.customers"));
        List<Object> params = new ArrayList<>();
        
        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }
        
        if (sex != null) {
            sql.append(" AND sex = ?");
            params.add(sex);
        }
        
        if (birthday != null) {
            sql.append(" AND birthday = ?");
            params.add(birthday);
        }
        
        sql.append(" ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        params.add((page - 1) * pageSize);
        params.add(pageSize);
        
        return queryForList(sql.toString(), this::mapCustomer, params.toArray());
    }
    
    public int getTotalCustomers(String name, Character sex, LocalDate birthday) {
        StringBuilder sql = new StringBuilder(sqlProps.getProperty("sql.select.customers.count"));
        List<Object> params = new ArrayList<>();
        
        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }
        
        if (sex != null) {
            sql.append(" AND sex = ?");
            params.add(sex);
        }
        
        if (birthday != null) {
            sql.append(" AND birthday = ?");
            params.add(birthday);
        }
        
        return queryForObject(sql.toString(), rs -> rs.getInt(1), params.toArray())
            .orElse(0);
    }
    
    // Other CRUD operations...
}
