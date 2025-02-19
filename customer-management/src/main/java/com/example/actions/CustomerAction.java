public class CustomerAction {

    // Method to add a new customer
    public String addCustomer(CustomerForm form) {
        // Logic to add customer
        return "success";
    }

    // Method to update an existing customer
    public String updateCustomer(CustomerForm form) {
        // Logic to update customer
        return "success";
    }

    // Method to delete customers
    public String deleteCustomers(List<Integer> customerIds) {
        // Logic to delete customers
        return "success";
    }

    // Method to list customers with pagination and search
    public String listCustomers(int currentPage, String name, String sex, String birthdayFrom, String birthdayTo) {
        // Logic to list customers
        return "success";
    }
}