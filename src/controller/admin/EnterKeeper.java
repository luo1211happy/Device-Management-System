package controller.admin;

import java.io.UnsupportedEncodingException;

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
 * Creation date: 01-22-2011
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class EnterKeeper extends Action {
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
		    String sn = request.getParameter("sn").trim();
		    String name = request.getParameter("name").trim();
		    String cipher = request.getParameter("password").trim();
		    String authority = request.getParameter("authority").trim();
		    int dep_sn = Integer.parseInt(request.getParameter("dep_sn").trim());

			Keeper keeper = new Keeper(sn,name,cipher,authority,dep_sn);
			  
			KeeperDAO user_dao = new KeeperDAO();
			user_dao.insert_keeper(keeper);
            request.setAttribute("prompt", "×¢²á³É¹¦£¡");
            HttpSession session = request.getSession();
            if(authority.equals("M") || authority.equals("B") || authority.equals("S"))
                session.setAttribute("adminer_list", new KeeperDAO().query_adminer());
            else if(authority.equals("N"))
                session.setAttribute("keeper_list", new KeeperDAO().query_keeper(dep_sn));
			return mapping.findForward("success");
 		  
        }catch(RuntimeException re){
    	    re.printStackTrace(System.err);
    	    request.setAttribute("prompt", "×¢²áÊ§°Ü£¡");
    	    return mapping.findForward("error");
        }
	}
}