/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagmentsystem.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import studentmanagmentsystem.db.MyConnection;

/**
 *
 * @author G.A
 */
public class Course {
    Connection con = MyConnection.getConnection();
    PreparedStatement ps = null;
    
    //get table max row
    public int getMax(){
        int id = 0;
        Statement st;
        try{
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select max(id) from course ;");
            while(rs.next()){
                id = rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id + 1;
    }
    
    //get Id 
    public boolean getID(int id){
        try {
            ps = con.prepareStatement("select * from student where id = ?");
             ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Home.jTextField27.setText(String.valueOf(id));
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Student id doesn't exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }
    
    public int countSemester(int id){
        int total = 0;
        try {
            ps = con.prepareStatement("select count(*) from course where student_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                total = rs.getInt(1);
            }
            if(total == 8){
                JOptionPane.showMessageDialog(null, "This student has completed all course.");
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("semester " + total);
        return total;
    }
    
    //check whether the student has already token this semester.
    public boolean isSemesterExist( int sid, int semesterNo){
        try {
            ps = con.prepareStatement("select * from course where student_id = ? and semester = ?");
            ps.setInt(1, sid);
            ps.setInt(2, semesterNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //check whether the student has already taken this course.
    public boolean isCourseExist( int sid, String courseNo,String courseName){
        String sql = "select * from course where student_id = ? and " + courseNo +  " = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sid);
            ps.setString(2, courseName);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //insert data nito course table
    public void insert(int id, int sid , int semesterNo,String course1, String course2,String course3, String course4,String course5){
        String sql = "insert into course values(?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, sid);
            ps.setInt(3, semesterNo);
            ps.setString(4, course1);
            ps.setString(5, course2);
            ps.setString(6, course3);
            ps.setString(7, course4);
            ps.setString(8, course5);
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Semester added successfully.");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }   
    }
    //get all the course value from database Course table
    public void getSCourseData(JTable table,String searchvalue){
        String sql = "select * from course where concat(id,student_id)"
                + " like ? order by id desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchvalue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while(rs.next()){
                Object[] row = new Object[8];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
