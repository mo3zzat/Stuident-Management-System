/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentmanagmentsystem;

import studentmanagmentsystem.student.MarkSheet;
import studentmanagmentsystem.student.Student;

/**
 *
 * @author G.A
 */
public class StudentManagmentSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MarkSheet ms = new MarkSheet();
        System.out.println(ms.getGPA(5));
    }
    
    
}
