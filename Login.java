import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class Login extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
        String type=req.getParameter("type");
		String user=req.getParameter("username");	
		String password=req.getParameter("password");
		HttpSession session=req.getSession();
		session.setAttribute("name",user);
		
		try{
			int flag=0;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s=con.createStatement();
			if(type.equals("User")){
			    ResultSet rs=s.executeQuery("select NAME,PASSWORD from employe");
			
			         while(rs.next())
			         {
				        if(user.equals(rs.getString(1))&& password.equals(rs.getString(2)))
				        {
				        	RequestDispatcher dispatcher=req.getRequestDispatcher("userlogin.html");
				         	dispatcher.include(req,res);
				         	out.println("welcome "+user);
				         	flag=1;
				         	break;
						 }
					 }
			
				
			  
			
				
				if(flag==0){
					JOptionPane.showMessageDialog(null,"invalid username or password","Alert Message",JOptionPane.WARNING_MESSAGE);
					RequestDispatcher dispatcher=req.getRequestDispatcher("FRONTPAGE.html");
					dispatcher.include(req,res);
				
				
				}
			}
			else{
				
				        if(user.equals("Harsh")&& password.equals("harsh1234"))
				        {
				        	RequestDispatcher dispatcher1=req.getRequestDispatcher("adminlogin.html");
				        	dispatcher1.forward(req,res);
				        	out.println("welcome "+ user);
						
						
						}
						else{
					    JOptionPane.showMessageDialog(null,"invalid username or password","Alert Message",JOptionPane.WARNING_MESSAGE);
					    RequestDispatcher dispatcher=req.getRequestDispatcher("FRONTPAGE.html");
					     dispatcher.include(req,res);
				
				
				}  
				
			}
		}
					
					
		
		catch(Exception e){}
			
	
	}
}













