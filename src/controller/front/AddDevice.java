/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.front;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import model.Device;
import model.DeviceDAO;
import model.Keeper;

/**
 * MyEclipse Struts Creation date: 11-03-2013
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class AddDevice extends Action {
    /*
     * Generated Methods
     */

    /**
     * Method execute
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            request.setCharacterEncoding("gb2312");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            int model_sn = Integer.parseInt(request.getParameter("model_sn").trim());          
            Date buy_date = Date.valueOf(request.getParameter("buy_date").trim());
            String keeper = ((Keeper)(request.getSession().getAttribute("keeper"))).getSn();
            
            int device_num = Integer.parseInt(request.getParameter("device_num"));           
            if (device_num <= 0) {
            	return mapping.findForward("error");
            }

            Device dev = new Device(model_sn, buy_date, keeper);
            new DeviceDAO().addDevice(dev, device_num);
            
            HttpSession session = request.getSession();
            session.setAttribute("dev_list", new DeviceDAO().getDevice(keeper));

            return mapping.findForward("success");

        } catch (RuntimeException re) {
            re.printStackTrace(System.err);
            return mapping.findForward("error");
        }
    }
}
