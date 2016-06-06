package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connect.connect;

/**
 * Servlet implementation class ShowMeta
 */
@WebServlet("/ShowMeta")
public class ShowMeta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private HttpSession session;     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMeta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(true);
		

		
			ResultSet resultTables = null;
			ResultSet resultColumns = null;
			ArrayList<String> tables = new ArrayList<>();
			PrintWriter out=response.getWriter();
			try  
			{
				connection = (Connection)session.getAttribute("connection");
				connection=connect.check(connection);
				
				DatabaseMetaData metadata = connection.getMetaData();
				String[] tableTypes = {"TABLE"};
				resultTables = metadata.getTables(null, "%", "%", tableTypes);
				
				while (resultTables.next()) 
				{
					tables.add(resultTables.getString("TABLE_NAME"));
				}

				for (String tableName : tables) 
				{
					out.println("<br>"+"Table: " + tableName);
					out.println("<br>"+"----------------");
					
					resultColumns = metadata.getColumns(null, "%", tableName, "%");
					
					while (resultColumns.next()) 
					{
						StringBuffer buffer = new StringBuffer();
						buffer.append(resultColumns.getString("COLUMN_NAME"));
						buffer.append(": ");
						buffer.append(resultColumns.getString("TYPE_NAME"));
						out.println("<br>"+buffer.toString());
					}
					
					out.println("<br>"+"");
				}
				out.println("<br><form action=\"DBControl.jsp\"><input type=\"submit\" value=\"Back to Menu\"></form>");
				
    				
			} 
			catch (SQLException e) 
			{
				out.println("Database error: Schema Unavailable");
			}
			finally 
			{
				try 
				{
					resultTables.close();
					resultColumns.close();
				} 
				catch (SQLException e) { }
			}
			
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
