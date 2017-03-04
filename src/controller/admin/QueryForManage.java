/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeviceDAO;
import model.Keeper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-20-2014
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class QueryForManage extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
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
            int model_sn = Integer.parseInt(request.getParameter("model_sn").trim()); 
            int item_sn = Integer.parseInt(request.getParameter("item_sn").trim());
            int class_sn = Integer.parseInt(request.getParameter("class_sn").trim());           
            String from_date = request.getParameter("from_date").trim();
            String to_date = request.getParameter("to_date").trim();
            String keeper_sn = request.getParameter("keeper_sn");
            int dep_sn = 0;
            if(request.getParameter("dep_sn") != null)
                dep_sn = Integer.parseInt(request.getParameter("dep_sn").trim());
 
            HttpSession session = request.getSession();
            Keeper admin = (Keeper)(session.getAttribute("admin"));
            if(admin.getAuthority().equals("S") || admin.getAuthority().equals("B")){
            	if(!keeper_sn.equals("0"))
            		session.setAttribute("dev_list", new DeviceDAO().getDevice(class_sn, item_sn, model_sn, from_date, to_date, keeper_sn));          	
            	else if(dep_sn != 0)
            		session.setAttribute("dev_list", new DeviceDAO().getDevice(class_sn, item_sn, model_sn, from_date, to_date, dep_sn));            	
            	else           		
                	session.setAttribute("dev_list", new DeviceDAO().getDevice(class_sn, item_sn, model_sn, from_date, to_date));
               
            }else if(admin.getAuthority().equals("M")){
            	if(keeper_sn == null || keeper_sn.equals("0"))
                	session.setAttribute("dev_list", new DeviceDAO().getDevice(class_sn, item_sn, model_sn, from_date, to_date, admin.getDep_sn()));
                else
                    session.setAttribute("dev_list", new DeviceDAO().getDevice(class_sn, item_sn, model_sn, from_date, to_date, keeper_sn));
                
            }
            
            if(request.getParameter("forward").equals("m_alarm_query"))
            	return mapping.findForward("m_alarm_query");
            else
                return mapping.findForward("success");
            

        } catch (RuntimeException re) {
            re.printStackTrace(System.err);
            return mapping.findForward("error");
        }
	}
}