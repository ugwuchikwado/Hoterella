package com.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;

/**
 *
 * @author AUSTINS
 */
public class HotelHelper extends HotelConst {

    Connection connection = null;
    ResultSet rst = null;
    PreparedStatement statement = null;

    int returnvalue = 1;

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/hotel_management";
        String user = "root";
        String password = "austinejoe";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //load driver
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
        }
        return connection;
    }

    public int registerUser(HotelBean bean) {
        String query = "INSERT INTO " + HotelConst.USER_TABLE + "(" + HotelConst.USER_NAME + ", " + HotelConst.USER_EMAIL
                + ", " + HotelConst.USER_PHONE + ", " + HotelConst.USER_ADDRESS + ", " + HotelConst.USER_GENDER
                + ", " + HotelConst.USER_NEXTOFKIN + ", " + HotelConst.USER_NEXTOFKINPHONE + ", " + HotelConst.USER_PASSWORD
                + ", " + HotelConst.USER_DATE + "hash) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getName());
            statement.setString(2, bean.getEmail());
            statement.setString(3, bean.getPhonenumber());
            statement.setString(4, bean.getAddress());
            statement.setString(5, bean.getGender());
            statement.setString(6, bean.getNextOfKin());
            statement.setString(7, bean.getNextOfKinPhone());
            statement.setString(8, bean.getPassword());
            statement.setDate(9, bean.getRegisterDate());
            statement.setString(10, bean.getHash());
            returnvalue = statement.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();

        }
        return returnvalue;
    }

    public ResultSet loginUser(HotelBean bean) {
        String query = "SELECT userid, " + HotelConst.USER_EMAIL + ", " + HotelConst.USER_PASSWORD + ", phonenumber FROM "
                + HotelConst.USER_TABLE + " WHERE " + HotelConst.USER_EMAIL + " = ? AND " + HotelConst.USER_PASSWORD + " = ? AND active=?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getEmail());
            statement.setString(2, bean.getPassword());
            statement.setInt(3, bean.getActive());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    //Register admin
    public int adminData(HotelBean bean) {
        String query = "INSERT INTO " + HotelConst.ADMIN_TABLE + "(name, " + HotelConst.USER_EMAIL + ", " + HotelConst.USER_PASSWORD
                + ", " + HotelConst.RESPONSIBILTY + ") VALUES (?,?,?,?)";

        try {
            statement = getConnection().prepareStatement(query);
             statement.setString(1, bean.getName());
            statement.setString(2, bean.getEmail());
            statement.setString(3, bean.getPassword());
            statement.setString(4, bean.getResponsibility());
            returnvalue = statement.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
            return returnvalue;
        }
    }

    //prevent dublicate of email in admin table
    public ResultSet emailExist(HotelBean bean) {
        String query = "SELECT email FROM admin WHERE email = ?";

        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getEmail());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    //prevent dublicate of email in user table
    public ResultSet userEmailExist(HotelBean bean) {
        String query = "SELECT " + HotelConst.USER_EMAIL + " FROM " + HotelConst.USER_TABLE + " WHERE "
                + HotelConst.USER_EMAIL + " =? ";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getEmail());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    public ResultSet recoverEmail(HotelBean bean) {
        String query = "SELECT " + HotelConst.USER_PASSWORD + " FROM " + HotelConst.ADMIN_TABLE + " WHERE "
                + HotelConst.USER_EMAIL + " = ?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getEmail());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    public ResultSet loginAdmin(HotelBean bean) {
        String query = "SELECT name, " + HotelConst.USER_EMAIL + ", " + HotelConst.USER_PASSWORD + " FROM "
                + HotelConst.ADMIN_TABLE + " WHERE " + HotelConst.USER_EMAIL + " = ? AND " + HotelConst.USER_PASSWORD + " = ?"
                + " AND " + HotelConst.RESPONSIBILTY + " = ? AND active = 1";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getEmail());
            statement.setString(2, bean.getPassword());
            statement.setString(3, bean.getResponsibility());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    public void addEmployee(HotelBean bean) {

        String query = "INSERT INTO " + HotelConst.EMPLOYEE + "(" + HotelConst.FIRSTNAME + ", " + HotelConst.LASTNAME
                + ", " + HotelConst.USER_EMAIL + ", " + HotelConst.USER_PHONE + ", " + HotelConst.USER_ADDRESS
                + ", " + HotelConst.DOB + ", " + HotelConst.USER_GENDER + ", " + HotelConst.USER_NEXTOFKIN + ", "
                + HotelConst.USER_NEXTOFKINPHONE + ", " + HotelConst.DEPARTMENT + ", " + HotelConst.APPROVALDATE
                + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getFirstName());
            statement.setString(2, bean.getLastName());
            statement.setString(3, bean.getEmail());
            statement.setString(4, bean.getPhonenumber());
            statement.setString(5, bean.getAddress());
            statement.setDate(6, bean.getDob());
            statement.setString(7, bean.getGender());
            statement.setString(8, bean.getNextOfKin());
            statement.setString(9, bean.getNextOfKinPhone());
            statement.setString(10, bean.getDepartment());
            statement.setDate(11, bean.getRegisterDate());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
        }

    }

    public int addRoom(HotelBean bean) {
        String query = "INSERT INTO " + HotelConst.ROOMTABLE + "(" + HotelConst.ROOMTYPE + ", " + HotelConst.ROOMPOSITION
                + ", " + HotelConst.ROOMNUMBER + ", " + HotelConst.UNITPRICE + ") VALUES (?,?,?,?)";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getRoomtype());
            statement.setString(2, bean.getRoomposition());
            statement.setString(3, bean.getRoomnumber());
            statement.setDouble(4, bean.getUnitprice());
            returnvalue = statement.executeUpdate();

        } catch (SQLException e) {
        }
        return returnvalue;
    }

    public ResultSet roomExist(HotelBean bean) {
        String query = "SELECT roomnumber FROM room WHERE roomnumber = ?";

        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getRoomnumber());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    public void addToBar(HotelBean bean) {
        String query = "INSERT INTO " + HotelConst.BAR_TABLE + "(" + HotelConst.BRAND + ", " + HotelConst.NAME
                + ", " + HotelConst.QUANTITY + ", " + HotelConst.UNITPRICE + ", " + HotelConst.PURCHASEDATE
                + ") VALUES (?,?,?,?,?)";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getBrand());
            statement.setString(2, bean.getName());
            statement.setInt(3, bean.getQuantity());
            statement.setDouble(4, bean.getUnitprice());
            statement.setDate(5, bean.getDate());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
        }
    }

    public ResultSet viewUser() {
        String query = "SELECT * FROM " + HotelConst.USER_TABLE;
        try {
            statement = getConnection().prepareStatement(query);
            rst = statement.executeQuery();
        } catch (SQLException e) {
            e.getMessage();
        }
        return rst;
    }
//delete user

    public void deleteUser(HotelBean bean) {
        String delete = "DELETE FROM " + HotelConst.USER_TABLE + " WHERE userid = ?";
        try {
            statement = getConnection().prepareStatement(delete);
            statement.setInt(1, bean.getUserid());
            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //delete employee
    public int deleteEmployee(HotelBean bean) {
        String deleteEmp = "DELETE FROM " + HotelConst.EMPLOYEE + " WHERE employeeid = ?";

        try {
            statement = getConnection().prepareStatement(deleteEmp);
            statement.setInt(1, bean.getId());
            returnvalue = statement.executeUpdate();
        } catch (SQLException e) {
        }
        return returnvalue;
    }

    public ResultSet checkRoom(HotelBean bean) {
        String check = "SELECT " + HotelConst.ROOMNUMBER + " FROM " + HotelConst.ROOMTABLE
                + " WHERE " + HotelConst.ROOMTYPE + " =? AND " + HotelConst.ROOMPOSITION + " = ?";

        try {
            statement = getConnection().prepareStatement(check);
            statement.setString(1, bean.getRoomtype());
            statement.setString(2, bean.getRoomposition());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    public int transferAdmin(newBean bean) {
        String query = "UPDATE " + HotelConst.ADMIN_TABLE + " SET " + HotelConst.RESPONSIBILTY + " = ? WHERE "
                + HotelConst.USER_EMAIL + " = ?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getResponsibility());
            statement.setString(2, bean.getEmail());
            returnvalue = statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
        return returnvalue;
    }

   
    public ResultSet selectRoomNumber(HotelBean bean) {
        String check = "SELECT roomnumber FROM room WHERE roomposition = ? AND roomtype = ?";

        try {
            statement = getConnection().prepareStatement(check);
            statement.setString(1, bean.getRoomposition());
            statement.setString(2, bean.getRoomtype());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

//    view all employee
    public ResultSet viewEmployee() {
        try {
            statement = getConnection().prepareStatement("SELECT * FROM " + HotelConst.EMPLOYEE + " ORDER BY employeeid ASC");
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }
    //update employee data

    public int updateEmployeeData(HotelBean bean) {
        String query = "UPDATE " + HotelConst.EMPLOYEE + " SET " + HotelConst.FIRSTNAME + " = ?, "
                + HotelConst.LASTNAME + " =?, " + HotelConst.USER_EMAIL + " = ?, " + HotelConst.USER_PHONE + " = ?,"
                + HotelConst.USER_ADDRESS + " = ?,  " + HotelConst.DOB + "=?," + HotelConst.USER_NEXTOFKIN + " =?,"
                + HotelConst.USER_NEXTOFKINPHONE + " =?," + HotelConst.DEPARTMENT + " =? WHERE employeeid = ?";

        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getFirstName());
            statement.setString(2, bean.getLastName());
            statement.setString(3, bean.getEmail());
            statement.setString(4, bean.getPhonenumber());
            statement.setString(5, bean.getAddress());
            statement.setDate(6, bean.getDob());
            statement.setString(7, bean.getNextOfKin());
            statement.setString(8, bean.getNextOfKinPhone());
            statement.setString(9, bean.getDepartment());
            statement.setInt(10, bean.getId());
            returnvalue = statement.executeUpdate();
        } catch (SQLException e) {
        }
        return returnvalue;
    }

    //contact us
    public int contactUs(newBean bean) {
        String query = "INSERT INTO messages  (email, messagetitle, messagebody) VALUES(?,?,?)";

        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getEmail());
            statement.setString(2, bean.getMessageTitle());
            statement.setString(3, bean.getMessageBody());
            returnvalue = statement.executeUpdate();
        } catch (SQLException e) {
        }
        return returnvalue;
    }

    //select room price
    public ResultSet selectRoomPrice(HotelBean bean) {
        String query = "SELECT " + HotelConst.UNITPRICE + " FROM " + HotelConst.ROOMTABLE + " WHERE " + HotelConst.ROOMNUMBER + " =?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getRoomnumber());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    //select user id
    public ResultSet selectUserId(HotelBean bean) {
        String query = "SELECT userid FROM user_data WHERE email = ?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getEmail());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }
   
    //creating reservation;
    public ResultSet roomNumber(HotelBean bean) {
        String query = "SELECT check_out_date, check_out_time FROM reservation WHERE room_number =?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getRoomnumber());
            rst = statement.executeQuery();

        } catch (SQLException e) {
        }
        return rst;
    }

    //print invoice
    public ResultSet PrepareInvoice(HotelBean bean) {
        String query = "SELECT user_data.phonenumber, reservation.name, reservation.email, reservation.check_in_date,"
                + " reservation.check_out_date, reservation.room_position, reservation.room_type, reservation.payment_method,"
                + " reservation.check_out_price FROM user_data JOIN reservation ON user_data.userid = reservation.reservation_id WHERE"
                + " user_data.userid = ?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, bean.getUserid());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    //view messages
    public ResultSet viewMessages() {
        String query = "SELECT * FROM messages";

        try {
            statement = getConnection().prepareStatement(query);
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    //archive messages
    public int deleteMessage(newBean bean) {
        String query = "DELETE FROM messages WHERE messageid = ?";

        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, bean.getMessageid());
            returnvalue = statement.executeUpdate();
        } catch (SQLException e) {
        }
        return returnvalue;
    }

    public int archiveMessage(newBean bean) {
        String query = "INSERT INTO message_archive (messageid, email, messagetitle, messagebody) VALUES(?,?,?,?)";

        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, bean.getMessageid());
            statement.setString(2, bean.getEmail());
            statement.setString(3, bean.getMessageTitle());
            statement.setString(4, bean.getMessageBody());
            returnvalue = statement.executeUpdate();
        } catch (SQLException e) {
        }
        return returnvalue;
    }

    public int purchaseFood(newBean bean) {
        String query = "INSERT INTO food(name, category, quantity, unitprice, totalprice, purchasedate)VALUES(?,?,?,?,?,?)";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getName());
            statement.setString(2, bean.getCategory());
            statement.setString(3, bean.getQuantity());
            statement.setInt(4, bean.getUnitprice());
            statement.setInt(5, bean.getTotalprice());
            statement.setString(6, bean.getPurchasedate());
            returnvalue = statement.executeUpdate();

        } catch (SQLException e) {
        }
        return returnvalue;
    }

    public int sellFood(newBean bean) {
        String query = "INSERT INTO soldfood (name, numberofplates, unitprice, totalprice, datesold, soldby) VALUES(?,?,?,?,?,?)";

        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getName());
            statement.setInt(2, bean.getNumber_of_plates());
            statement.setInt(3, bean.getUnitprice());
            statement.setInt(4, bean.getTotalprice());
            statement.setString(5, bean.getDatesold());
            statement.setString(6, bean.getCheckedby());
            returnvalue = statement.executeUpdate();
        } catch (SQLException e) {
        }
        return returnvalue;
    }
    
     
    public ResultSet selectUser(HotelBean bean) {
        String query = "SELECT * FROM user_data WHERE email = ?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getEmail());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
        return rst;
    }

    public ResultSet searchBarContent(HotelBean bean) {
        String query = "SELECT * FROM bar WHERE name LIKE ?%";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, bean.getName());
            rst = statement.executeQuery();
        } catch (SQLException e) {
        }
         return rst;
    }
   public ResultSet checkCEO(){
       String Query = "SELECT responsibility FROM admin WHERE responsibility = 'CEO'";
       try {
           statement = getConnection().prepareStatement(Query);
           rst = statement.executeQuery();
       } catch (SQLException e) {
           
       }
       return rst;
   }
}
