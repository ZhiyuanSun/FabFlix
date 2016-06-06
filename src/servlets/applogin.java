package servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.connect;
/**
 * Servlet implementation class applogin
 */
@WebServlet("/applogin")
public class applogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Connection conn=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public applogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email= request.getParameter("email");
		String password= request.getParameter("password");

		try {
			conn=connect.check(conn);
			
			response.getWriter().println(login_auth(email,password,conn));
			response.getWriter().flush();
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  int login_auth(String email, String password, Connection connection) throws SQLException
	{
		String sql = "SELECT count(*) FROM customers WHERE email = ? AND password = ?";
		
		PreparedStatement verifyStatement = connection.prepareStatement(sql);
		verifyStatement.setString(1, email);
		verifyStatement.setString(2, password);
		
		ResultSet result = verifyStatement.executeQuery();
		result.next();
		return result.getInt("count(*)");

	}
	
	
}
