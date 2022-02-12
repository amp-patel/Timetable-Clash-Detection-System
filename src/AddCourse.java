import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourse extends Container {
    private JTextField textField1;
    private JButton addCourseButton;
    public JPanel AddCourseJPanel;

    private AddCourseHandlerKt addCourseHandlerKt = new AddCourseHandlerKt();
    private AddCourseKt addCourseKt;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddCourse");
        frame.setContentPane(new AddCourse().AddCourseJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AddCourse() {
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    //add course data to db by persistence
                    addCourseKt = addCourseHandlerKt.createAddCourse(textField1.getText());

                    addCourseHandlerKt.saveAddCourse(addCourseKt);
                    JOptionPane.showMessageDialog(null, "Success: Data is Stored. " +
                            "Please press refresh button to show your data.");
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Unsuccessful: Data is not Stored.");
                }
            }
        });
    }
}
























