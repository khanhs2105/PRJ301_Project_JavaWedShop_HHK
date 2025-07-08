/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAL;

import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


public class DAO {
    
    private String status = "ok";
    private Connection con;
    private Vector<User> std;
  

    public static final DAO Ins = new DAO();
        public DAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
            status = "Error at connecttion" + e.getMessage();
        }
    }
        public User checkLogin(String u, String p) {
    String sql = "SELECT * FROM Users WHERE name = ? AND password = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, u);
        ps.setString(2, p);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User us = new User();
           us.setId(rs.getInt("id"));
           us.setName(rs.getString("name"));
           us.setPassword( rs.getString("password"));
           us.setAge(rs.getInt("age"));
           us.setGender(rs.getBoolean("gender"));
           us.setRole(rs.getInt("role"));
           us.setLocked(rs.getBoolean("locked"));
            return us;
        }
    } catch (Exception e) {
        status = "Lỗi tại checkLogin: " + e.getMessage();
    }
    return null;
       
}
        public boolean register(User user) {
    String sql = "INSERT INTO Users(name, password, age, gender, role, locked) VALUES (?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setInt(3, user.getAge());
        ps.setBoolean(4, user.isGender());
        ps.setInt(5, 0); // role mặc định là 0 (user)
        ps.setBoolean(6, false); // chưa bị khóa
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        status = "Lỗi tại register: " + e.getMessage();
        return false;
    }
}
        public String getStatus() {
    return status;
}
private Vector<User> users; // danh sách người dùng

    public Vector<User> getStd() {
        return std;
    }

    public void setStd(Vector<User> std) {
        this.std = std;
    }

   

    public void setUsers(Vector<User> users) {
        this.users = users;
    }

public void loadUser() {
    String sql = "SELECT * FROM Users";
    users = new Vector<>();

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setPassword(rs.getString("password"));
            u.setAge(rs.getInt("age"));
            u.setGender(rs.getBoolean("gender"));
            u.setRole(rs.getInt("role"));
            u.setLocked(rs.getBoolean("locked"));
            users.add(u);
        }
    } catch (Exception e) {
        status = "Lỗi tại loadUsers: " + e.getMessage();
    }
}
 public Vector<User> getUsers() {
        return users;
    }
}
