/* Practical 2 Question 4  [Basic CRUD] */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MaintainStudentsFrame extends JFrame {

    private JButton jbtnCreate = new JButton("Create");
    private JButton jbtnRetrieve = new JButton("Retrieve");
    private JButton jbtnUpdate = new JButton("Update");
    private JButton jbtnDelete = new JButton("Delete");

    private ArrayList<String> studentList = new ArrayList<String>();
    private ArrayList<String> copyStudentList = new ArrayList<String>();

    public MaintainStudentsFrame() {
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

    //Get index
    private int getStudentIndex(int index) {
        int selected = -1;

        if (studentList.isEmpty() || index > studentList.size()) {
            return -1;
        }
        for (int i = 0; i < studentList.size(); i++) {

            if (studentList.get(index - 1).equals(studentList.get(i))) {
                selected = i;
                break;
            }
        }
        return selected;
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
                String studentName = JOptionPane.showInputDialog("Enter Student Index [ 1 - " + studentList.size() + " ]:");
                if (!"".equals(studentName)) {
                    int no = Integer.parseInt(studentName);
                    int selected = getStudentIndex(no);;
                    if (selected != -1) {
                        JOptionPane.showMessageDialog(null, "Student Name is " + studentList.get(selected));
                    } else {
                        JOptionPane.showMessageDialog(null, "Can't find the student",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Student index cannot be empty!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
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
                copyStudentList.addAll(studentList); //copy old arraylist to new copyStudentList arraylist
                StringBuilder namelist = new StringBuilder();
                for (int i = 0; i < studentList.size(); i++) {
                    namelist.append(i + 1 + ".").append(" " + studentList.get(i)).append("\n");
                }
                try {
                    String studentName = JOptionPane.showInputDialog(namelist + "Enter student index to be updated:");
                    if (!"".equals(studentName)) {
                        int no = Integer.parseInt(studentName);
                        int selected = getStudentIndex(no);
                        if (selected != -1) {
                            String newName = JOptionPane.showInputDialog("Current student name is " + studentList.get(selected) + " . Enter Updated name:");
                            studentList.set(selected, newName);
                            JOptionPane.showMessageDialog(null, "Student Name " + copyStudentList.get(selected) + " has change to " + newName,
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
                        JOptionPane.showMessageDialog(null, "Student index cannot be empty!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Non a numeirc value");
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

                String studentName = JOptionPane.showInputDialog(namelist + "Enter student index to be deleted: ");
                if (!"".equals(studentName)) {
                    int no = Integer.parseInt(studentName);
                    int selected = getStudentIndex(no);
                    if (selected != -1) {
                        int option = JOptionPane.showConfirmDialog(null, "Delete " + studentList.get(selected) + " ?");
                        if (option == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "Student " + studentList.get(selected) + " deleted successfully",
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
                    JOptionPane.showMessageDialog(null, "Student index cannot be empty!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    public static void main(String[] args) {
        MaintainStudentsFrame frame = new MaintainStudentsFrame();
        frame.setTitle("Maintain Students");
        frame.setSize(400, 100);
        frame.setLocation(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
