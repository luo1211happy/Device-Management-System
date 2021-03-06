/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package controller.front;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecordDAO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-16-2014
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UpdateRepair extends Action {
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
            int sn = Integer.parseInt(request.getParameter("sn"));
            String repair = request.getParameter("repair");
            
            java.util.Date dt = new java.util.Date();      
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
            Date repair_date = Date.valueOf(sdf.format(dt));
            
            new RecordDAO().updateRepair(sn, repair, repair_date);
          
            return null;

        } catch (RuntimeException re) {
            re.printStackTrace(System.err);
            return null;
        }
	}
}