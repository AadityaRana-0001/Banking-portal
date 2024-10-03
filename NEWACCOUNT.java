import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;
public class NEWACCOUNT extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
	    String user=req.getParameter("username");
	    String father=req.getParameter("fathername");
		String adhar=req.getParameter("useraadhar");
		String email=req.getParameter("emailname");
		String accountype=req.getParameter("accounttype");
		String balance=req.getParameter("balanceno");
		String gender=req.getParameter("gendername");
		String update="";
		
		
        Random random=new Random();		
		String s="123456789";
		char[] otp=new char[11];
		for(int i=0;i<11;i++){
			otp[i]=s.charAt(random.nextInt(s.length()));
			}
			
			String strArray[]=new String[otp.length];
			for(int i=0;i<otp.length;i++){
				strArray[i]=String.valueOf(otp[i]);
			}
			String s1=Arrays.toString(strArray);
			
			String res1="";
			for(String num:strArray){
				res1+=num;
			}
			try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			PreparedStatement ps=con.prepareStatement("insert into accountdata values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1,user);
			ps.setString(2,res1);
			ps.setString(3,father);
			ps.setString(4,adhar);
			ps.setString(5,email);
			ps.setString(6,accountype);
			ps.setString(7,balance);
			ps.setString(8,gender);
			ps.setString(9,update);
			ps.executeUpdate();
			PreparedStatement ps1=con.prepareStatement("select *from accountdata where ACCOUNTNO='"+res1+"'");
		
			
				ResultSet rs=ps1.executeQuery();

				while(rs.next()){

					out.println("Name= "+rs.getString(1));
					out.println("Account No= "+rs.getString(2));
					out.println("Father Name= "+rs.getString(3));
					out.println("Aadhar= "+rs.getString(4));
					out.println("E-mail Id= "+rs.getString(5));
					out.println("Account Type= "+rs.getString(6));
					out.println("Balance= "+rs.getString(7));
					out.println("Gender= "+rs.getString(8));
				}
				RequestDispatcher disp=req.getRequestDispatcher("userlogin.html");
				disp.include(req,res);
			
				out.println("<br>CONGRATULATIONS :::: ");
                                out.println("<br><br><br>YOU REGISTERED SUCCESFULLY :::: ");
			
		
			
			
			}
			catch(Exception e){out.println(e);}
			out.close();
			
			
		
	}
}