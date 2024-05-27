import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoListGUI extends JFrame {
    private ArrayList<String> todoItems;
    private JList<String> todoList;
    private DefaultListModel<String> listModel;
    private JTextField todoInputField;

    public ToDoListGUI() {
        setTitle("To-Do List");
        setSize(600, 500); // Increased window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set sky blue background color
        getContentPane().setBackground(new Color(173, 216, 230));

        todoItems = new ArrayList<>();
        listModel = new DefaultListModel<>();

        // Create and configure the list
        todoList = new JList<>(listModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setFont(new Font("Arial", Font.PLAIN, 16)); // Increased font size
        JScrollPane scrollPane = new JScrollPane(todoList);

        // Create and configure input field
        todoInputField = new JTextField();
        todoInputField.setPreferredSize(new Dimension(300, 30)); // Increased input field size
        todoInputField.setBackground(Color.WHITE); // Set input field background to white
        todoInputField.setFont(new Font("Arial", Font.PLAIN, 16)); // Increased font size

        // Create and configure buttons
        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(255, 192, 203)); // Set button background color to pink
        addButton.setForeground(Color.BLACK);
        addButton.setFont(new Font("Arial", Font.BOLD, 16)); // Increased font size
        addButton.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 192, 203), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10))); // Add pink border
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTodoItem();
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(new Color(255, 192, 203)); // Set button background color to pink
        removeButton.setForeground(Color.BLACK);
        removeButton.setFont(new Font("Arial", Font.BOLD, 16)); // Increased font size
         
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTodoItem();
            }
        });

        // Create panel for buttons and input field
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(173, 216, 230)); // Set panel background color to sky blue
        inputPanel.add(todoInputField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        // Add components to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);
    }

    private void addTodoItem() {
        String todoItem = todoInputField.getText().trim();
        if (!todoItem.isEmpty()) {
            todoItems.add(todoItem);
            listModel.addElement(todoItem);
            todoInputField.setText("");
        }
    }

    private void removeTodoItem() {
        int selectedIndex = todoList.getSelectedIndex();
        if (selectedIndex != -1) {
            todoItems.remove(selectedIndex);
            listModel.remove(selectedIndex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToDoListGUI todoListGUI = new ToDoListGUI();
                todoListGUI.setVisible(true);
            }
        });
    }
}
