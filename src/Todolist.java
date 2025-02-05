import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Todolist extends JFrame implements ActionListener {
    //taskPanel will act as the container for the taskcomponentpanel
    //taskComponentPanel will store all of the taskComponents
    private JPanel taskPanel, taskComponentPanel;


    public Todolist(){
        setTitle("Hii");
        setPreferredSize(CommonContacts.GUI_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponents();
    }

    private void addGuiComponents(){
        //banner text
        JLabel bannerLabel = new JLabel("<html><h1>TO DO List</h1></html>");
        bannerLabel.setBounds(
                (CommonContacts.GUI_SIZE.width-bannerLabel.getPreferredSize().width)/2,
                15,
                CommonContacts.BANNER_SIZE.width,
                CommonContacts.BANNER_SIZE.height
        );

        //taskPanel
        taskPanel = new JPanel();

        //taskComponentPanel
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        //add Scrolling to the taskPanel;
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8,70,CommonContacts.TASKPANEL_SIZE.width,CommonContacts.TASKPANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setMaximumSize(CommonContacts.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //changing the speed of the scrollbar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        //add task button
        JButton addTaskButton = new JButton("<html><h2>Add Task<></h2></html>");
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.setBounds(
                -5,
                CommonContacts.GUI_SIZE.height-88,
                CommonContacts.TASKBUTTON_SIZE.width,
                CommonContacts.TASKBUTTON_SIZE.height
        );
        addTaskButton.addActionListener(this);




//        add to frame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("<html><h2>Add Task<></h2></html>")){
            //create a task component
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            //make the previous task appear disable
            if(taskComponentPanel.getComponentCount() > 1){
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount()-2);
                previousTask.getTaskField().setBackground(null);
            }

            //make the task request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}
