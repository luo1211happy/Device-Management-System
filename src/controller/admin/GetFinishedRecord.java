/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Keeper;
import model.RecordDAO;

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
public class GetFinishedRecord extends Action {
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
            
            String from_date = request.getParameter("from_date").trim();
            String to_date = request.getParameter("to_date").trim();
            String dev_sn = request.getParameter("dev_sn").trim();
            
            HttpSession session = request.getSession();
			Keeper admin = (Keeper)(session.getAttribute("admin"));
            if(admin.getAuthority().equals("S") || admin.getAuthority().equals("B"))
            	request.setAttribute("rec_list", new RecordDAO().getFinishedRecords(from_date, to_date, dev_sn ));
            else if(admin.getAuthority().equals("M"))
            	request.setAttribute("rec_list", new RecordDAO().getFinishedRecords(admin.getDep_sn(), from_date, to_date, dev_sn ));
 
            if(request.getParameter("forward").equals("self"))
                return mapping.findForward("self");
            else if(request.getParameter("forward").equals("print"))
            	return mapping.findForward("print");
            else 
            	return null;

        } catch (RuntimeException re) {
            re.printStackTrace(System.err);
            return null;
        }
	}
}