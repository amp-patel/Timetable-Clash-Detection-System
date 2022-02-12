import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddModule extends Container {
    private JTextField textField1;
    private JButton addModuleButton;
    public JPanel AddModuleJPanel;

    private AddModuleHandlerKt addModuleHandlerKt = new AddModuleHandlerKt();
    private AddModuleKt addModuleKt;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddModule");
        frame.setContentPane(new AddModule().AddModuleJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AddModule() {
        addModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    //add module data to db by persistence
                    addModuleKt = addModuleHandlerKt.createAddModule(textField1.getText());

                    addModuleHandlerKt.saveAddModule(addModuleKt);
                    JOptionPane.showMessageDialog(null, "Success: " +
                            "Data is Stored. Please press refresh button to show your data.");
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


















