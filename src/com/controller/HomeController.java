package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));
		String ip = in.readLine(); //you get the IP as a String
		System.out.println(ip);
		
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException ex) {
	        }
	        String url = "jdbc:mysql://sql139.main-hosting.eu/u409859217_azure";
	        String user = "u409859217_azure";
	        String password = "30MIeUY1LrOA";
		 
//	        String url = "jdbc:mysql://localhost:3306/media";
//	        String user = "root";
//	        String password = "1234";
//	        
	        String query = "SELECT VERSION()";

	        try (Connection con = DriverManager.getConnection(url, user, password);
	            Statement st = (Statement) con.createStatement();
	            ResultSet rs = st.executeQuery(query)) {
	            if (rs.next()) {      
	                out.println("<h1>Msql version "+rs.getString(1)+"</h1>");
	            }} catch (SQLException ex) {
	           ex.printStackTrace();
	           out.println("<h1>Hello Servlet Get "+ip+"</h1>");
	        }
	        
			out.println("</body>");
			out.println("</html>");	
	}
}