
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import Model.Books;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.*;

@WebServlet(urlPatterns={"/add","/books","/delete"})
public class App extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("deprecation")
	@Override
		 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 String check = request.getServletPath();
			 if(check.equals("/add")) {
				 StringBuilder jsonBuffer = new StringBuilder();
			 
			 try(BufferedReader reader = request.getReader()){
				 String line;
				 while((line=reader.readLine())!=null) {
					 jsonBuffer.append(line);
				 }
			 }
			 String jsonString = jsonBuffer.toString();

	         // Step 2: Parse JSON to get the book name

	         JSONObject jsonObject = new JSONObject(jsonString);
	         String bookName = jsonObject.getString("bookName");
	         String isbn = jsonObject.getString("isbn");

				try{// 1. configuring hibernate
				Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

				// 2. create sessionfactory
				SessionFactory sessionFactory = configuration.buildSessionFactory();

				// 3. Get Session object
				Session session = sessionFactory.openSession();

				// 4. Starting Transaction
				Transaction transaction = session.beginTransaction();
				Books book = new Books();
				book.setBookName(bookName);
				book.setIsbn(Integer.parseInt(isbn));
				session.save(book);
				transaction.commit();
				System.out.println(book);
				PrintWriter out = response.getWriter();
				 JSONObject jo=new JSONObject();
				 jo.put("meesage", "Added Succesfull ");
				 out.print(jo.toString());

			} catch (HibernateException e) {
				System.out.println(e.getMessage());
				System.out.println("error");
			}
		 }
			 else  if (check.endsWith("/delete")) {
		    	 StringBuilder jsonBuffer = new StringBuilder();
				 
				 try(BufferedReader reader = request.getReader()){
					 String line;
					 while((line=reader.readLine())!=null) {
						 jsonBuffer.append(line);
					 }
				 }
				 String jsonString = jsonBuffer.toString();

		         // Step 2: Parse JSON to get the book name

		         JSONObject jsonObject = new JSONObject(jsonString);
		         String	bookId = jsonObject.getString("id");
		        // Get 'id' from query parameter or path parameter

		        if (bookId != null) {
		            try {
		                // 1. Configuring Hibernate
		                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		                // 2. Create SessionFactory
		                SessionFactory sessionFactory = configuration.buildSessionFactory();

		                // 3. Get Session object
		                Session session = sessionFactory.openSession();

		                // 4. Starting Transaction
		                Transaction transaction = session.beginTransaction();

		                // Find the book by id
		                Books book = session.get(Books.class, Integer.parseInt(bookId)); // Assuming bookId is an integer

		                if (book != null) {
		                    // Delete the book from the database
		                    session.delete(book);
		                    transaction.commit(); // Commit transaction
		                    session.close();

		                    // Send response
		                    PrintWriter out = response.getWriter();
		                    JSONObject jo = new JSONObject();
		                    jo.put("message", "Book deleted successfully");
		                    out.print(jo.toString());
		                } else {
		                    // If the book is not found
		                    PrintWriter out = response.getWriter();
		                    JSONObject jo = new JSONObject();
		                    jo.put("message", "Book not found");
		                    out.print(jo.toString());
		                }

		            } catch (HibernateException e) {
		                System.out.println(e.getMessage());
		                System.out.println("error");
		                // Error handling
		                PrintWriter out = response.getWriter();
		                JSONObject jo = new JSONObject();
		                jo.put("message", "Error deleting book");
		                out.print(jo.toString());
		            }
		        } else {
		            // If 'id' is not provided
		            PrintWriter out = response.getWriter();
		            JSONObject jo = new JSONObject();
		            jo.put("message", "ID is required to delete a book");
		            out.print(jo.toString());
		        }
		    }	 }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String check = request.getServletPath();
		if(check.endsWith("/books")) {
	        try {
	            // 1. Configuring Hibernate
	            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

	            // 2. Create SessionFactory
	            SessionFactory sessionFactory = configuration.buildSessionFactory();

	            // 3. Get Session object
	            Session session = sessionFactory.openSession();

	            // 4. Starting Transaction
	            Transaction transaction = session.beginTransaction();
	            Query<Books> query = session.createQuery("from Books", Books.class);
	            List<Books> books = query.getResultList();
	            session.getTransaction().commit();
	            session.close();

	            // Prepare response
	            PrintWriter out = response.getWriter();
	            JSONArray jsonArr = new JSONArray();
	            
	            // Iterate through the books list
	            for (Books book : books) {
	                JSONObject obj = new JSONObject();  // Move this inside the loop to create a new object for each book
	                obj.put("id", String.valueOf(book.getId()));
	                obj.put("bookName", book.getBookName());
	                obj.put("isbn", String.valueOf(book.getIsbn()));
	                jsonArr.put(obj);
	            }
	            
	            out.print(jsonArr.toString());  // Send the JSON response

	        } catch (HibernateException e) {
	            System.out.println(e.getMessage());
	            System.out.println("error");
	        }
	    }
	}
	
   
}


