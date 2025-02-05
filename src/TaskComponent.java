import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;

    public JTextPane getTaskField(){
        return taskField;
    }

    private JPanel parentPanel;
    public TaskComponent(JPanel parentPanel){
        this.parentPanel = parentPanel;

        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonContacts.CHECKBOX_SIZE);
        checkBox.addActionListener(this);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonContacts.DELETE_BUTTON_SIZE);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);

        taskField = new JTextPane();
        taskField.setPreferredSize(CommonContacts.TASKFIELD_SIZE);
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskField.setContentType("text/html");
        taskField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null);
            }

        });

        add(checkBox);
        add(taskField);
        add(deleteButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>","");
            taskField.setText("<html><s>"+taskText+"</s></html>");
        }else if(!checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>","");
            taskField.setText(taskText);
        }
        if(e.getActionCommand().equalsIgnoreCase("X")){
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}