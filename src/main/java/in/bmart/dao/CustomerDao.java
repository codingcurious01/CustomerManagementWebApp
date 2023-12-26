package in.bmart.dao;

import java.sql.*;
import java.util.ArrayList;

import in.bmart.model.Customer;

public class CustomerDao {
    private static Connection cn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static Customer c = null;
    private static int rowsAffected = 0;

    private static final String stateIdQry = "select state_id from state where state_name = ?";
    private static final String genderIdQry = "select gender_id from gender where gender_type = ?";
    private static final String insertQry = "insert into customer_original(customer_name, customer_mobile, customer_purchased_state_id, customer_email, gender_id) values (?,?,?,?,?)";
    private static final String selectAllQry = "select c.customer_id as id, c.customer_name as name, c.customer_mobile as mobile, s.state_name as state, c.customer_email as email, g.gender_type as gender from customer_original c join  gender g on g.gender_id = c.gender_id join state s on s.state_id = c.customer_purchased_state_id order by id asc";
    private static final String selectCustomerByIdQry = "select c.customer_name as name, c.customer_mobile as mobile, s.state_name as state, c.customer_email as email, g.gender_type as gender from customer_original c join  gender g on g.gender_id = c.gender_id join state s on s.state_id = c.customer_purchased_state_id where c.customer_id = ?";
    private static final String updateCustomerByIdQry = "update customer_original set customer_name = ?, customer_mobile = ?, customer_purchased_state_id = ?, customer_email = ?, gender_id = ? where customer_id = ?";
    private static final String deleteCustomerById = "delete from customer_original where customer_id = ?";
    
    // Below method will delete customer data based on id
    public static int deleteCustomerById(Integer id) {
	try {
	    cn = MyDbConnection.myConnection();

	    ps = cn.prepareStatement(deleteCustomerById);
	    ps.setInt(1, id);

	    rowsAffected = ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (ps != null) {
		try {
		    ps.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (cn != null) {
		try {
		    cn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return rowsAffected;
    }
    
    // Below method will update customer data based on id.
    public static int updateCustomerById(Customer updateCustomer) {
	try {
	    // getStateId
	    int sId = getStateId(updateCustomer.getState());
	    // getGenderId
	    int gId = getGenderId(updateCustomer.getGender());

	    cn = MyDbConnection.myConnection();

	    ps = cn.prepareStatement(updateCustomerByIdQry);
	    ps.setString(1, updateCustomer.getName());
	    ps.setLong(2, updateCustomer.getMobile());
	    ps.setInt(3, sId);
	    ps.setString(4, updateCustomer.getEmail());
	    ps.setInt(5, gId);
	    ps.setInt(6, updateCustomer.getId());

	    rowsAffected = ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (ps != null) {
		try {
		    ps.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (cn != null) {
		try {
		    cn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return rowsAffected;
    }

    // Below method will get customer bean based on ID
    public static Customer getCustomerById(Integer id) {
	try {
	    cn = MyDbConnection.myConnection();
	    ps = cn.prepareStatement(selectCustomerByIdQry);
	    ps.setInt(1, id);
	    rs = ps.executeQuery();
	    rs.next();
	    String n = rs.getString("name");
	    Long m = rs.getLong("mobile");
	    String s = rs.getString("state");
	    String e = rs.getString("email");
	    String g = rs.getString("gender");

	    c = new Customer(id, n, m, s, e, g);
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (ps != null) {
		try {
		    ps.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (cn != null) {
		try {
		    cn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return c;
    }

    // Below method will get all customers data from customer_original table by
    // joining -
    // - gender and state tables.
    public static ArrayList<Customer> getAllCustomer() {
	ArrayList<Customer> al = new ArrayList<Customer>();
	try {
	    cn = MyDbConnection.myConnection();
	    ps = cn.prepareStatement(selectAllQry);

	    rs = ps.executeQuery();
	    while (rs.next()) {
		Integer i = rs.getInt("id");
		String n = rs.getString("name");
		Long m = rs.getLong("mobile");
		String s = rs.getString("state");
		String e = rs.getString("email");
		String g = rs.getString("gender");

		c = new Customer(i, n, m, s, e, g);

		al.add(c);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (ps != null) {
		try {
		    ps.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (cn != null) {
		try {
		    cn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return al;
    }

    // Below method will insert customer data into customer_original table
    public static int insertNewCustomerDao(Customer newCustomer) {
	try {
	    // getStateId
	    int sId = getStateId(newCustomer.getState());
	    // getGenderId
	    int gId = getGenderId(newCustomer.getGender());

	    cn = MyDbConnection.myConnection();

	    ps = cn.prepareStatement(insertQry);
	    ps.setString(1, newCustomer.getName());
	    ps.setLong(2, newCustomer.getMobile());
	    ps.setInt(3, sId);
	    ps.setString(4, newCustomer.getEmail());
	    ps.setInt(5, gId);

	    rowsAffected = ps.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (ps != null) {
		try {
		    ps.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (cn != null) {
		try {
		    cn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return rowsAffected;
    }

    // Below method will get the GenderId:
    private static int getGenderId(String genderType) {
	int genderId = 0;

	try {
	    cn = MyDbConnection.myConnection();

	    ps = cn.prepareStatement(genderIdQry);
	    ps.setString(1, genderType);

	    rs = ps.executeQuery();

	    boolean data = rs.next();
	    if (data) {
		genderId = rs.getInt("gender_id");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (ps != null) {
		try {
		    ps.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (cn != null) {
		try {
		    cn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return genderId;
    }

    // Below method will get the StateId:
    private static int getStateId(String stateName) {
	int stateId = 0;

	try {
	    cn = MyDbConnection.myConnection();

	    ps = cn.prepareStatement(stateIdQry);
	    ps.setString(1, stateName);

	    rs = ps.executeQuery();

	    boolean data = rs.next();
	    if (data) {
		stateId = rs.getInt("state_id");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (ps != null) {
		try {
		    ps.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (cn != null) {
		try {
		    cn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return stateId;
    }

}
