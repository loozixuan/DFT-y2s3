/* Practical 2 Question 4  [Basic CRUD] */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class P2Q4 extends JFrame {

    private JButton jbtnCreate = new JButton("Create");
    private JButton jbtnRetrieve = new JButton("Retrieve");
    private JButton jbtnUpdate = new JButton("Update");
    private JButton jbtnDelete = new JButton("Delete");

    private ArrayList<String> studentList = new ArrayList<String>();

    public P2Q4() {
        setLayout(new FlowLayout());

        add(jbtnCreate);
        add(jbtnRetrieve);
        add(jbtnUpdate);
        add(jbtnDelete);

        jbtnCreate.addActionListener(new CreateStudentListenerClass());
        jbtnRetrieve.addActionListener(new RetrieveStudentListenerClass());
        jbtnUpdate.addActionListener(new UpdateStudentListenerClass());
        jbtnDelete.addActionListener(new RemoveStudentListenerClass());
    }

    // Create New Student
    private class CreateStudentListenerClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String studentName = JOptionPane.showInputDialog("Enter new Student Name : ");
            if (!"".equals(studentName)) {
                if (studentName != null) {
                    studentList.add(studentName);
                    JOptionPane.showMessageDialog(null,
                            studentName
                    );
                }
            } else {
                JOptionPane.showMessageDialog(null, "Student Name cannot be empty!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // Retrieve Student List
    private class RetrieveStudentListenerClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (studentList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Student List is empty!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                StringBuilder namelist = new StringBuilder();
                for (int i = 0; i < studentList.size(); i++) {
                    namelist.append(i + 1 + ".").append(" " + studentList.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(null,
                        namelist,
                        "Updated Student List",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    //Get index based on name input
    private int getStudentIndex(String name) {
        int selected = -1;

        if (studentList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < studentList.size(); i++) {
            if (name.equals(studentList.get(i))) {
                selected = i;
                break;
            }
        }
        return selected;
    }

    // Update Student Name
    private class UpdateStudentListenerClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (studentList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Student List is empty!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                StringBuilder namelist = new StringBuilder();
                for (int i = 0; i < studentList.size(); i++) {
                    namelist.append(i + 1 + ".").append(" " + studentList.get(i)).append("\n");
                }

                String studentName = JOptionPane.showInputDialog(namelist + "Enter Name to be updated:");
                if (!"".equals(studentName)) {
                    int selected = getStudentIndex(studentName);
                    if (selected != -1) {
                        String newName = JOptionPane.showInputDialog("Current student name is " + studentName + " . Enter Updated name:");
                        studentList.set(selected, newName);
                        JOptionPane.showMessageDialog(null, "Student Name " + studentName + " has change to " + newName,
                                "UpdatedStudentList",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(null, "Can't find the student",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Student Name cannot be empty!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    // Remove Student
    private class RemoveStudentListenerClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (studentList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Student List is empty!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                StringBuilder namelist = new StringBuilder();
                for (int i = 0; i < studentList.size(); i++) {
                    namelist.append(i + 1 + ".").append(" " + studentList.get(i)).append("\n");
                }

                String studentName = JOptionPane.showInputDialog(namelist + "Enter Student name to be deleted: ");
                if (!"".equals(studentName)) {
                    int selected = getStudentIndex(studentName);
                    if (selected != -1) {
                        int option = JOptionPane.YES_OPTION;
                        if (option == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "Student " + studentName + " deleted successfully",
                                    "Deletion",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            studentList.remove(selected);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Can't find the student",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Student Name cannot be empty!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    public static void main(String[] args) {
        P2Q4 frame = new P2Q4();
        frame.setTitle("Maintain Students");
        frame.setSize(400, 100);
        frame.setLocation(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
