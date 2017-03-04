/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Department;
import model.DepartmentDAO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 05-22-2014
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UpdateDepartment extends Action {
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
		try{
			int sn = Integer.parseInt(request.getParameter("sn").trim());
			String name = request.getParameter("name").trim();
			
			new DepartmentDAO().updateDepartment(sn, name);	    	
			ArrayList<Department> dep_list = new DepartmentDAO().getDepartments();
			
			HttpSession session = request.getSession();
			session.setAttribute("dep_list", dep_list);			
			return mapping.findForward("success");
			
		}catch(RuntimeException re){
			re.printStackTrace(System.err);
			return mapping.findForward("error");
			
		}
	}
}