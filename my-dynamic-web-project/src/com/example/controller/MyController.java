package com.example.controller;

import com.example.model.MyModel;
import com.example.service.MyService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyController extends Action {

    private MyService myService;

    public MyController() {
        myService = new MyService();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            MyModel model = new MyModel();
            // Set properties of model from form data
            myService.create(model);
            return mapping.findForward("success");
        } else if ("update".equals(action)) {
            MyModel model = new MyModel();
            // Set properties of model from form data
            myService.update(model);
            return mapping.findForward("success");
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            myService.delete(id);
            return mapping.findForward("success");
        } else if ("retrieve".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            MyModel model = myService.retrieve(id);
            request.setAttribute("model", model);
            return mapping.findForward("view");
        }

        return mapping.findForward("error");
    }
}