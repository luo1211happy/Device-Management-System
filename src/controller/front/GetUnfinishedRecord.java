/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.front;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RecordDAO;
import model.Keeper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-14-2014
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class GetUnfinishedRecord extends Action {
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
            request.setCharacterEncoding("gb2312");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            
            HttpSession session = request.getSession();
            String keeper = ((Keeper)(session.getAttribute("keeper"))).getSn();
            request.setAttribute("rec_list", new RecordDAO().getUnfinishedRecords(keeper));

            return mapping.findForward("success");

        } catch (RuntimeException re) {
            re.printStackTrace(System.err);
            return null;
        }
	}
}