package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayMaintainInformation extends JFrame {

    private JButton jbtnProgramme = new JButton("Maintain Programme");
    private JButton jtbnStudent = new JButton("Maintain Student");

    public DisplayMaintainInformation() {
        JPanel jpButtonGrp = new JPanel(new GridLayout(2, 1));
        jpButtonGrp.add(jbtnProgramme);
        jpButtonGrp.add(jtbnStudent);
        add(jpButtonGrp);

        jbtnProgramme.addActionListener(new ProgrammeListener());
        jtbnStudent.addActionListener(new StudentListener());
    }

    private class ProgrammeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MaintainProgrammeFrame frm = new MaintainProgrammeFrame();
            frm.setTitle("Programme CRUD");
            frm.setSize(600, 200);
            frm.setLocationRelativeTo(null);
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setVisible(true);
            setVisible(false);
        }
    }

    private class StudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MaintainStudentFrame frm = new MaintainStudentFrame();
            frm.setTitle("Student CRUD");
            frm.setSize(600, 600);
            frm.setLocationRelativeTo(null);
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        DisplayMaintainInformation frm = new DisplayMaintainInformation();
        frm.setTitle("Maintain Information");
        frm.setSize(450, 300);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }

}
