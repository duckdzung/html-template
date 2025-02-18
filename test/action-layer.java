package com.crud.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.crud.utils.MessageUtils;

public abstract class BaseAction extends HttpServlet {
    protected MessageUtils messageUtils;
    
    @Override
    public void init() throws ServletException {
        super.init();
        messageUtils = new MessageUtils();
    }
    
    protected void forward(String path, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/" + path + ".jsp")
               .forward(request, response);
    }
    
    protected void setMessage(HttpServletRequest request, String key) {
        request.setAttribute("message", messageUtils.getMessage(key));
    }
    
    protected void setError(HttpServletRequest request, String key) {
        request.setAttribute("error", messageUtils.getMessage(key));
    }
}

package com.crud.action;

import com.crud.business.CustomerBusiness;
import com.crud.model.Customer;
import com.crud.constants.MessageConstants;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customers/*")
public class CustomerAction extends BaseAction {
    private final CustomerBusiness customerBusiness;
    
    public CustomerAction() {
        this.customerBusiness = new CustomerBusiness();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || "/".equals(pathInfo)) {
            handleList(request, response);
        } else if ("/edit".equals(pathInfo)) {
            handleEdit(request, response);
        } else if ("/create".equals(pathInfo)) {
            forward("customer-form", request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if ("/save".equals(pathInfo)) {
            handleSave(request, response);
        } else if ("/delete".equals(pathInfo)) {
            handleDelete(request, response);
        }
    }
    
    private void handleList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int page = Integer.parseInt(request.getParameter("page", "1"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize", "10"));
            String name = request.getParameter("name");
            String sexStr = request.getParameter("sex");
            Character sex = sexStr != null && !sexStr.isEmpty() ? sexStr.charAt(0) : null;
            LocalDate birthday = request.getParameter("birthday") != null ? 
                LocalDate.parse(request.getParameter("birthday")) : null;
            
            request.setAttribute("customers", 
                customerBusiness.searchCustomers(name, sex, birthday, page, pageSize));
            request.setAttribute("totalPages", 
                customerBusiness.getTotalPages(name, sex, birthday, pageSize));
            request.setAttribute("currentPage", page);
            
            forward("customer-list", request, response);
        } catch (Exception e) {
            setError(request, MessageConstants.ERROR_CUSTOMER_LIST);
            forward("customer-list", request, response);
        }
    }
    
    private void handleSave(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Customer customer = new Customer();
            customer.setName(request.getParameter("name"));
            customer.setSex(request.getParameter("sex").charAt(0));
            customer.setBirthday(LocalDate.parse(request.getParameter("birthday")));
            customer.setEmail(request.getParameter("email"));
            
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                customer.setId(Long.parseLong(id));
                customerBusiness.updateCustomer(customer);
                setMessage(request, MessageConstants.SUCCESS_CUSTOMER_UPDATE);
            } else {
                customerBusiness.createCustomer(customer);
                setMessage(request, MessageConstants.SUCCESS_CUSTOMER_CREATE);
            }
            
            response.sendRedirect(request.getContextPath() + "/customers");
        } catch (Exception e) {
            setError(request, e.getMessage());
            forward("customer-form", request, response);
        }
    }
    
    // Additional handler methods...
}
