/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAL;

import Models.User;
import java.sql.Connection;
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
        
        
        User alo = new User();
        String returnName(){
            return alo.getName();
        }
        private int ngusidan = 100;
}
