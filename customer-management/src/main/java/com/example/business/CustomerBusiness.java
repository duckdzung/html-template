public class CustomerBusiness {

    private CustomerDAO customerDAO;

    public CustomerBusiness() {
        this.customerDAO = new CustomerDAO();
    }

    public void addCustomer(Customer customer) {
        // Logic to add customer
        customerDAO.insertCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        // Logic to update customer
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomers(List<Integer> customerIds) {
        // Logic to delete multiple customers
        for (Integer id : customerIds) {
            customerDAO.deleteCustomer(id);
        }
    }

    public List<Customer> getAllCustomers() {
        // Logic to retrieve all customers
        return customerDAO.getAllCustomers();
    }

    public Customer getCustomerById(int id) {
        // Logic to retrieve a customer by ID
        return customerDAO.getCustomerById(id);
    }

    public List<Customer> searchCustomers(String name, String sex, Date birthdayFrom, Date birthdayTo) {
        // Logic to search customers based on criteria
        return customerDAO.searchCustomers(name, sex, birthdayFrom, birthdayTo);
    }
}