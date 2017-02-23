package edu.nju.tag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class CheckLoginHandler extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	  public int doEndTag() throws JspException {

	        HttpSession session = pageContext.getSession();

	        if(session.getAttribute("selections") == null){
	            HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
	            try {
	                session.invalidate();
	                response.sendRedirect("login.jsp");
	            }catch (Exception e){

	            }
	          //EVAL_BODY_TAG，表示继续计算一次Body
	            return EVAL_BODY_AGAIN;
	        }else{
	        	 //直到返回SKIP_BODY才继续往下执行 要求JSP容器忽略主体，进入下一步的处理工作
	            return SKIP_BODY;
	        }

	    }
}
