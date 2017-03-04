/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemDAO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 02-28-2014
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class DeleteItem extends Action {
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
            int sn = Integer.parseInt(request.getParameter("sn").trim());
            

            ItemDAO itemDao = new ItemDAO();
            itemDao.deleteItem(sn);

            HttpSession session = request.getSession();
            
            itemDao = new ItemDAO();
            session.setAttribute("item_list", itemDao.getItem());

            return mapping.findForward("success");

        } catch (RuntimeException re) {
            re.printStackTrace(System.err);
            return mapping.findForward("error");
        }
	}
}