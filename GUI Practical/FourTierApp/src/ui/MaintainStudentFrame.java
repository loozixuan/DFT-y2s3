package ui;

import control.MaintainProgrammeControl;
import control.MaintainStudentControl;
import domain.Programme;
import domain.Student;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MaintainStudentFrame extends JFrame {

    private MaintainProgrammeControl progControl;
    private MaintainStudentControl studControl;
    private JTextField jtfStudID = new JTextField();
    private JTextField jtfStudIC = new JTextField();
    private JTextField jtfStudName = new JTextField();
    private JTextField jtfLevel = new JTextField();
    private JTextField jtfProgCode = new JTextField();
    private JTextField jtfYear = new JTextField();
    private JButton jbtAdd = new JButton("Create");
    private JButton jbtRetrieve = new JButton("Retrieve");
    private JButton jbtUpdate = new JButton("Update");
    private JButton jbtDelete = new JButton("Delete");

    public MaintainStudentFrame() {
        studControl = new MaintainStudentControl();
        JPanel jpCenter = new JPanel(new GridLayout(6, 2));
        jpCenter.add(new JLabel("Student ID"));
        jpCenter.add(jtfStudID);
        jpCenter.add(new JLabel("Student IC"));
        jpCenter.add(jtfStudIC);
        jpCenter.add(new JLabel("Student Name"));
        jpCenter.add(jtfStudName);
        jpCenter.add(new JLabel("Level"));
        jpCenter.add(jtfLevel);
        jpCenter.add(new JLabel("Programme Code"));
        jpCenter.add(jtfProgCode);
        jpCenter.add(new JLabel("Year"));
        jpCenter.add(jtfYear);
        add(jpCenter);

        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtAdd);
        jpSouth.add(jbtRetrieve);
        jpSouth.add(jbtUpdate);
        jpSouth.add(jbtDelete);
        add(jpSouth, BorderLayout.SOUTH);

        jbtRetrieve.addActionListener(new RetrieveListener());
        jbtAdd.addActionListener(new AddListener());
        jbtUpdate.addActionListener(new UpdateListener());
        jbtDelete.addActionListener(new DeleteListener());

    }

    private class RetrieveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String id = jtfStudID.getText();
                Student student = studControl.selectRecord(id);

                if (student != null) {
                    jtfStudID.setText(student.getId());
                    jtfStudIC.setText(student.getIc());
                    jtfStudName.setText(student.getName());
                    jtfLevel.setText(String.valueOf(student.getLevel()));
                    jtfProgCode.setText(String.valueOf(student.getProgramme().getCode()));
                    jtfYear.setText(String.valueOf(student.getYear()));
                } else {
                    JOptionPane.showMessageDialog(null, "No such Student.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String id = jtfStudID.getText();
                String ic = jtfStudIC.getText();
                String name = jtfStudName.getText();
                String level = jtfLevel.getText();
                String progCode = jtfProgCode.getText();
                String year = jtfYear.getText();

                Student student = studControl.selectRecord(id);

                if (student == null) {
                    studControl.addRecord(new Student(id, ic, name, level.charAt(0), new Programme(progCode), Integer.parseInt(year)));
                    JOptionPane.showMessageDialog(null, "New Student added.");
                } else {
                    JOptionPane.showMessageDialog(null, "Record Existing.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = jtfStudID.getText();
            String ic = jtfStudIC.getText();
            String name = jtfStudName.getText();
            String level = jtfLevel.getText();
            String progCode = jtfProgCode.getText();
            String year = jtfYear.getText();

            Student student;
            try {
                student = studControl.selectRecord(id);

                if (student != null) {
                    studControl.updateRecord(new Student(id, ic, name, level.charAt(0), new Programme(progCode), Integer.parseInt(year)));
                    JOptionPane.showMessageDialog(null, "Record updated.");
                } else {
                    JOptionPane.showMessageDialog(null, "Record not Existing.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String studId = jtfStudID.getText();
                Student student = studControl.selectRecord(studId);
                if (student != null) {
                    jtfStudID.setText(student.getId());
                    jtfStudIC.setText(student.getIc());
                    jtfStudName.setText(student.getName());
                    jtfLevel.setText(String.valueOf(student.getLevel()));
                    jtfProgCode.setText(String.valueOf(student.getProgramme().getCode()));
                    jtfYear.setText(String.valueOf(student.getYear()));

                    int option = JOptionPane.showConfirmDialog(null, "Are you sure? ");
                    if (option == JOptionPane.YES_OPTION) {
                        studControl.deleteRecord(studId);
                        JOptionPane.showMessageDialog(null, "Record deleted.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No such Student.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        MaintainStudentFrame frm = new MaintainStudentFrame();
        frm.setTitle("Student CRUD");
        frm.setSize(600, 600);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }

}
