/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.front;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Keeper;
import model.Location;
import model.LocationDAO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-10-2014
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class AddLocation extends Action {
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
            String name = request.getParameter("location").trim();
            if (name.equals("")) {
            	request.setAttribute("prompt", "λ�ò���Ϊ�գ�");
                return mapping.findForward("error");
            }
            HttpSession session = request.getSession();
            String keeper = ((Keeper)(session.getAttribute("keeper"))).getSn();
            Location loc = new Location(keeper, name);
            new LocationDAO().addLocation(loc);

            session.setAttribute("loc_list", new LocationDAO().getLocation(keeper));
            request.setAttribute("prompt", "λ�����ӳɹ���");
            return mapping.findForward("success");

        } catch (RuntimeException re) {
            re.printStackTrace(System.err);
            request.setAttribute("prompt", "λ������ʧ�ܣ�");
            return mapping.findForward("error");
        }
	}
}