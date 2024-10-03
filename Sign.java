import java.io.*;
import javax.servlet.*;
import java.sql.*;

public class Sign implements Servlet
{
	public void init(ServletConfig s)
	{}

	public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String n=req.getParameter("username");
		String p=req.getParameter("userpassword");
		String cp=req.getParameter("userconfirm");
		String e=req.getParameter("useremail");
		String c=req.getParameter("usercontact");

		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
			("jdbc:oracle:thin:@localhost:1521:xe","system","system");

			PreparedStatement ps=con.prepareStatement
			("insert into employe values(?,?,?,?,?)");

			ps.setString(1,n);
			ps.setString(2,p);
			ps.setString(3,cp);
			ps.setString(4,e);
			ps.setString(5,c);

			ps.executeUpdate();

			RequestDispatcher rd=req.getRequestDispatcher("FRONTPAGE.html");
			rd.include(req,res);
			pw.println( n +"   "+"you are registered succesfully");
		}

		catch(Exception ex)
		{
			System.out.println(ex);
		}
		pw.close();

	}
	public void destroy(){}
	
	public String getServletInfo()
	{
		return(null);	
	}
	public ServletConfig getServletConfig()
	{
		return(null);
	}
}