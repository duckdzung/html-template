public class CustomerAction extends Action {

    private CustomerBusiness customerBusiness = new CustomerBusiness();

    public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        CustomerForm customerForm = (CustomerForm) form;
        
        try {
            customerBusiness.addCustomer(customerForm);
            return mapping.findForward("success");
        }
        catch (BusinessException e) {
            // Add errors to request
            for (Map.Entry<String, String> error : e.getErrors().entrySet()) {
                ActionErrors errors = new ActionErrors();
                errors.add(error.getKey(), new ActionMessage(error.getValue()));
                saveErrors(request, errors);
            }
            return mapping.findForward("failure");
        }
    }
}