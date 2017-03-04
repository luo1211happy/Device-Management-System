/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.front;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Device;
import model.Item;
import model.Keeper;
import model.Location;
import model.Model;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-19-2014
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ExportElapsedYears extends Action {
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
		
		HttpSession session = request.getSession();
		if(session.getAttribute("keeper_list") == null)
			model.ExportDevice.print((ArrayList<Device>)(session.getAttribute("dev_list")), (ArrayList<Item>)(session.getAttribute("item_list")),(ArrayList<Model>)(session.getAttribute("model_list")),null,((Keeper)(session.getAttribute("keeper"))).getName(), request, response);
		else
		    model.ExportDevice.print((ArrayList<Device>)(session.getAttribute("dev_list")), (ArrayList<Item>)(session.getAttribute("item_list")),(ArrayList<Model>)(session.getAttribute("model_list")),(ArrayList<Keeper>)(session.getAttribute("keeper_list")),null, request, response);

		return null;
	}
}