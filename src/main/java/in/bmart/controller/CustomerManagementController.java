package in.bmart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.bmart.dao.AdminDao;
import in.bmart.dao.CustomerDao;
import in.bmart.model.Customer;

@WebServlet("/")
public class CustomerManagementController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String path = request.getServletPath();

	switch (path) {
	case "/logout":
	    adminLogout(request, response);
	    break;
	case "/delete":
	    deleteCustomer(request, response);
	    break;
	case "/update":
	    updateCustomer(request, response);
	    break;
	case "/editForm":
	    editCustomer(request, response);
	    break;
	case "/insert":
	    insertNewCustomer(request, response);
	    break;
	case "/insertForm":
	    getCustomerForm(request, response);
	    break;
	case "/adminValidate":
	    validateAdminGetAllCustomers(request, response);
	    break;
	case "/adminLogin":
	    getAdminLoginPage(request, response);
	    break;
	default:
	    getHomePage(request, response);
	    break;
	}
    }
    
    private void adminLogout(HttpServletRequest request, HttpServletResponse response) 
    {
	HttpSession session = request.getSession();
	session.removeAttribute("username");
	session.invalidate();
	
	try {
	    response.sendRedirect("admin-login.jsp");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
    {
	// Read admin given new data of customer.
	Integer i = Integer.parseInt(request.getParameter("id"));
	
	// call method to execute JDBC code to update customer with new values.
	int rowsAffected = CustomerDao.deleteCustomerById(i);
	// if update is done redirect to admin to customer list page with updated customer table.
	if(rowsAffected > 0) {
	    getCustomerListWithData(request, response);
	}
	else {
	    try {
		response.getWriter().append("<h1>Something went wrong! Please go back and check</h1>");
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	}
    }
    
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) 
    {
	// Read admin given new data of customer.
	Integer i = Integer.parseInt(request.getParameter("id"));
	String n = request.getParameter("name");
	Long m = Long.parseLong(request.getParameter("mobile"));
	String s = request.getParameter("state");
	String e = request.getParameter("email");
	String g = request.getParameter("gender");
	
	// store above data in Customer bean object
	Customer updateBean = new Customer(i, n, m, s, e, g);
	
	// call method to execute JDBC code to update customer with new values.
	int rowsAffected = CustomerDao.updateCustomerById(updateBean);
	// if update is done redirect to admin to customer list page with updated customer table.
	if(rowsAffected > 0) {
	    getCustomerListWithData(request, response);
	}
	else {
	    try {
		response.getWriter().append("<h1>Something went wrong! Please go back and check</h1>");
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	}
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) 
    {
	Integer i = Integer.parseInt(request.getParameter("id"));
	try {
	    	// Get the row data from customer table to edit one customer based on id.
	    	Customer previousData = CustomerDao.getCustomerById(i);
	    	// Add the above customer bean to attribute.
	    	request.setAttribute("customer", previousData);
		// Redirect to customer-list page
		RequestDispatcher rd = request.getRequestDispatcher("customer-form.jsp");
		rd.forward(request, response);

	    } catch (ServletException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
    }

    private static void getCustomerListWithData(HttpServletRequest request, HttpServletResponse response) {
	 try {
		// Get all customers data.
		ArrayList<Customer> alCustomers = CustomerDao.getAllCustomer();

		// Add ArrayList to attribute.
		request.setAttribute("alCustomers", alCustomers);

		// Redirect to customer-list page
		RequestDispatcher rd = request.getRequestDispatcher("customer-list.jsp");
		rd.forward(request, response);

	    } catch (ServletException e1) {
		e1.printStackTrace();
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
    }

    private void insertNewCustomer(HttpServletRequest request, HttpServletResponse response) {

	// Read the Customer data from customer form to insert.
	String n = request.getParameter("name");
	Long m = Long.parseLong(request.getParameter("mobile"));
	String s = request.getParameter("state");
	String e = request.getParameter("email");
	String g = request.getParameter("gender");
	if(g == null)
	    g = "Null";
	
	// Storing above data as bean object.
	Customer c = new Customer(n, m, s, e, g);

	// Call DAO method to insert data into customer_original table.
	int rowsAffected = CustomerDao.insertNewCustomerDao(c);

	if (rowsAffected > 0) {
	    // If new Customer is inserted redirect to customer -list with customer details
	    getCustomerListWithData(request, response);
	} 
	else {
	    try {
		response.getWriter().append("<h1>Insert Failed! Please go back and check</h1>");
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	}
    }

    private void getCustomerForm(HttpServletRequest request, HttpServletResponse response) {
	try {
	    RequestDispatcher rd = request.getRequestDispatcher("customer-form.jsp");
	    rd.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void validateAdminGetAllCustomers(HttpServletRequest request, HttpServletResponse response) {

	// Read Admin data from admin-form page:
	String user = request.getParameter("username");
	String pass = request.getParameter("password");

	// Call DAO method to valid admin isValid from admin table
	boolean result = AdminDao.validateAdminDao(user, pass);

	// If valid redirect to customer-list page with all customer data or to error-
	// -page
	if (result) 
	{
	    HttpSession session = request.getSession();
	    session.setAttribute("username", user);
	    // redirect admin to customer-list page with customer details
	    getCustomerListWithData(request, response);
	} else {
	    try {
		// Redirect to error page
		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		rd.forward(request, response);
	    } catch (ServletException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

    }

    private void getHomePage(HttpServletRequest req, HttpServletResponse res) {
	try {
	    RequestDispatcher rd = req.getRequestDispatcher("home-page.jsp");
	    rd.forward(req, res);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void getAdminLoginPage(HttpServletRequest req, HttpServletResponse res) {
	try {
	    RequestDispatcher rd = req.getRequestDispatcher("admin-login.jsp");
	    rd.forward(req, res);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	doGet(request, response);
    }

}
