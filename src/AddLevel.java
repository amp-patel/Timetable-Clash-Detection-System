import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLevel extends Container {
    private JTextField textField1;
    private JButton addLevelButton;
    public JPanel AddLevelJPanel;

    private AddLevelHandlerKt addLevelHandlerKt = new AddLevelHandlerKt();
    private AddLevelKt addLevelKt;



    public static void main(String[] args) {
        JFrame frame = new JFrame("AddLevel");
        frame.setContentPane(new AddLevel().AddLevelJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AddLevel() {
        addLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    //add level data to db by persistence
                    addLevelKt = addLevelHandlerKt.createAddLevel(textField1.getText());

                    addLevelHandlerKt.saveAddLevel(addLevelKt);
                    JOptionPane.showMessageDialog(null, "Success: Data is Stored. " +
                            "Please press refresh button to show your data.");
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Unsuccessful:" +
                            " Data is not Stored.");
                }
            }
        });
    }
}















