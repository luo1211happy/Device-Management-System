/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Keeper;
import model.KeeperDAO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 05-23-2014
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class DeleteKeeper extends Action {
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
            String sn = request.getParameter("sn").trim();
            
            KeeperDAO keeper_dao = new KeeperDAO();
            keeper_dao.delete_keeper(sn);
            
            HttpSession session = request.getSession();
            Keeper admin = (Keeper)(session.getAttribute("admin"));
            if(admin.getAuthority().equals("S"))
                session.setAttribute("adminer_list", new KeeperDAO().query_adminer());
            else if(admin.getAuthority().equals("M"))
                session.setAttribute("keeper_list", new KeeperDAO().query_keeper(admin.getDep_sn()));
            return mapping.findForward("success");

        } catch (RuntimeException re) {
            re.printStackTrace(System.err);
            return mapping.findForward("error");
        }
	}
}