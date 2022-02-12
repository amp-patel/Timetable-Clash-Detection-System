import Clash_Detection.ClashDetection;
import Clash_Detection.ScalaClashDetection;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class MainGUI extends Container{

    public ClashDetection clashCheck = new ClashDetection();

    public int id;

    private AddTimeTableHandler addTimeTableHandler = new AddTimeTableHandler();
    private AddTimeTable addTimeTable;

    private CourseHandler courseHandler = new CourseHandler();
    private Course course;

    private LevelHandler levelHandler = new LevelHandler();
    private Level level;

    private ModuleHandler moduleHandler = new ModuleHandler();
    private Module module;

    private RoomHandler roomHandler = new RoomHandler();
    private Room room;

    private TimeHandler timeHandler = new TimeHandler();
    private Time time;

    public boolean clashKotlin2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(new MainGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;

    private JButton AddTimeTable;
    private JPanel mainPanel;
    private JComboBox courseName;
    private JComboBox moduleName;
    private JComboBox roomName;
    private JComboBox setectTime;
    private JTable view_time_table;
    private JComboBox levelName;
    private JButton refreshButton;
    private JButton addLevelButton;
    private JButton clashDetectionByKotlinButton;
    private JButton addCourseButton;
    private JButton addModulesButton;
    private JButton updateTimeButton;
    private JButton deleteButton;
    private JButton clashDetectionUsingScalaButton;

    //db connection
    public void connect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/time_table_scheduling",
                    "root", "");
            System.out.println("success");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    public MainGUI() {
        connect();

        //get all Levels to Dropdownlist
        level = levelHandler.createLevel();
        String[] level2 = levelHandler.saveLevel(level);
        levelName.setModel(new DefaultComboBoxModel(level2));

        //get all Courses to Dropdownlist
        course = courseHandler.createCourse();
        String[] course2 = courseHandler.saveCourse(course);
        courseName.setModel(new DefaultComboBoxModel(course2));

        //get all Modules to Dropdownlist
        module = moduleHandler.createModule();
        String[] module2 = moduleHandler.saveModule(module);
        moduleName.setModel(new DefaultComboBoxModel(module2));

        //get all Rooms to Dropdownlist
        room = roomHandler.createRoom();
        String[] room2 = roomHandler.saveRoom(room);
        roomName.setModel(new DefaultComboBoxModel(room2));

        //get all Time to Dropdownlist
        time = timeHandler.createTime();
        String[] time2 = timeHandler.saveTime(time);
        setectTime.setModel(new DefaultComboBoxModel(time2));

        createTable();

        AddTimeTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add all data to db by persistence
                try {
                    addTimeTable = addTimeTableHandler.createAddTimeTable(
                            levelName.getSelectedItem().toString(), courseName.getSelectedItem().toString(),
                            moduleName.getSelectedItem().toString(), roomName.getSelectedItem().toString(),
                            setectTime.getSelectedItem().toString());

                    addTimeTableHandler.saveAddTimeTable(addTimeTable);
                    JOptionPane.showMessageDialog(null, "Success: Data is Stored. " +
                            "Please press refresh button to show your data.");
                    createTable();
                    boolean clashcheck = clashCheck.ClashCheck();

                    if(clashcheck)
                    {
                        JOptionPane.showMessageDialog(null, "Clash found by Kotlin");
                        System.out.println(clashcheck);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Clash not found by Kotlin");
                        System.out.println(clashcheck);
                    }
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Unsuccessful: Data is not Stored.");
                }
            }
        });

        //open new window for adding new level
        addLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("AddLevel");
                frame.setContentPane(new AddLevel().AddLevelJPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });

        //open new window for adding new course
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AddCourse");
                frame.setContentPane(new AddCourse().AddCourseJPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        //open new window for adding new module
        addModulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AddModule");
                frame.setContentPane(new AddModule().AddModuleJPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        //refresh all the content which are shown in this UI including dropdown list and table
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createTable();

                //Level Dropdownlist
                level = levelHandler.createLevel();
                String[] level2 = levelHandler.saveLevel(level);
                levelName.setModel(new DefaultComboBoxModel(level2));

                //Course Dropdownlist
                course = courseHandler.createCourse();
                String[] course2 = courseHandler.saveCourse(course);
                courseName.setModel(new DefaultComboBoxModel(course2));

                //Module Dropdownlist
                module = moduleHandler.createModule();
                String[] module2 = moduleHandler.saveModule(module);
                moduleName.setModel(new DefaultComboBoxModel(module2));

                //Room Dropdownlist
                room = roomHandler.createRoom();
                String[] room2 = roomHandler.saveRoom(room);
                roomName.setModel(new DefaultComboBoxModel(room2));

                //Time Dropdownlist
                time = timeHandler.createTime();
                String[] time2 = timeHandler.saveTime(time);
                setectTime.setModel(new DefaultComboBoxModel(time2));

            }
        });

        //clash detection
        clashDetectionByKotlinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTable();

                boolean clashcheck = clashCheck.ClashCheck();


                if(clashcheck)
                {
                    JOptionPane.showMessageDialog(null, "Clash found by Kotlin");
                    System.out.println(clashcheck);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Clash not found by Kotlin");
                    System.out.println(clashcheck);
                }
            }
        });
        view_time_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel)view_time_table.getModel();
                int selectedRowIndex = view_time_table.getSelectedRow();
//                levelName.setSelectedIndex((Integer) model.getValueAt(selectedRowIndex, 1));

              id = (int) model.getValueAt(selectedRowIndex, 0);
                System.out.println(model.getValueAt(selectedRowIndex, 1).toString());
                System.out.println(model.getValueAt(selectedRowIndex, 2).toString());



//                try {
//                    addTimeTable = addTimeTableHandler.createAddTimeTable(
//                            levelName.getSelectedItem().toString(), courseName.getSelectedItem().toString(),
//                            moduleName.getSelectedItem().toString(), roomName.getSelectedItem().toString(),
//                            setectTime.getSelectedItem().toString());
//
//                    addTimeTableHandler.saveAddTimeTable(addTimeTable);
//                    JOptionPane.showMessageDialog(null, "Success: Data is Stored. " +
//                            "Please press refresh button to show your data.");
//                }
//                catch (Exception ex)
//                {
//                    System.out.println(ex);
//                    JOptionPane.showMessageDialog(null, "Unsuccessful: Data is not Stored.");
//                }
            }
        });
        updateTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pst = con.prepareStatement("update time_table set time = ? where id = ?");
                    pst.setString(1,setectTime.getSelectedItem().toString());
                    pst.setString(2, String.valueOf(id));

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Time Updated");

                    createTable();
                    boolean clashcheck = clashCheck.ClashCheck();

                    if(clashcheck)
                    {
                        JOptionPane.showMessageDialog(null, "Clash found by Kotlin");
                        System.out.println(clashcheck);
                    }

                }
                catch (Exception ex)
                {

                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pst = con.prepareStatement("delete from time_table where id = ?");
                    pst.setString(1, String.valueOf(id));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted");
                    createTable();

                }
                catch (Exception ex)
                {

                }
            }
        });

        //Scala Clash Detection
        clashDetectionUsingScalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(ScalaClashDetection.main());

                if(ScalaClashDetection.main())
                {
                    JOptionPane.showMessageDialog(null, "Clash found by Scala");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Clash not found by Scala");
                }
            }
        });
    }

    // show all data to table
    private void createTable()
    {
        try {
            pst = con.prepareStatement("select * from time_table");
            ResultSet rs = pst.executeQuery();
            view_time_table.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
