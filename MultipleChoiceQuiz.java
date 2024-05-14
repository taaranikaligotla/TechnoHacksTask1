import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MultipleChoiceQuiz extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton optionA, optionB, optionC, optionD;
    private JButton submitButton, startButton, playAgainButton, exitButton;
    private ButtonGroup optionGroup;

    private String[][] questions = {
            {"What is the capital of France?", "Paris", "London", "Berlin", "Madrid", "SkyBlue"},
            {"Who won the Australian Grand Prix 2024?", "Carlos Sainz Jr.", "Max Verstappen", "Charles Leclerc", "Lando Norris", "LightCoral"},
            {"Who is the CEO of OpenAI?", "Sam Altman", "Elon Musk", "Jeff Bezos", "Sundar Pichai", "LightGreen"},
            {"Which album by Taylor Swift won Album of the Year at the 2021 Grammy Awards?", "Folklore", "Lover", "1989", "Red", "LightPink"},
            {"Who won the Best Actor award at the 2021 Oscars?", "Anthony Hopkins", "Chadwick Boseman", "Riz Ahmed", "Gary Oldman", "LightGreen"}
    };
    private int currentQuestionIndex = 0;
    private int score = 0;

    private Map<String, Color> colorMap;

    public MultipleChoiceQuiz() {
    setTitle("Multiple Choice Quiz");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    initializeColorMap();

    // Initialize start button
    startButton = new JButton("Start");
    startButton.addActionListener(this);
    startButton.setFont(new Font("Arial", Font.BOLD, 30));

    // Initialize question label
    questionLabel = new JLabel("Are you ready to start the quiz?");
    questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
    questionLabel.setForeground(Color.WHITE);

    // Create start panel
    JPanel startPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    startPanel.setBackground(colorMap.get("SkyBlue"));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10); // Padding
    gbc.anchor = GridBagConstraints.CENTER;
    startPanel.add(questionLabel, gbc);
    gbc.gridy++;
    startPanel.add(startButton, gbc);

    getContentPane().add(startPanel);
    setVisible(true);

    }

    private void initializeColorMap() {
        colorMap = new HashMap<>();
        colorMap.put("SkyBlue", new Color(135, 206, 235));
        colorMap.put("LightCoral", new Color(240, 128, 128));
        colorMap.put("LightGreen", new Color(144, 238, 144));
        colorMap.put("LightSkyBlue", new Color(135, 206, 250));
        colorMap.put("LightPink", new Color(255, 182, 193));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startQuiz();
        } else if (e.getSource() == submitButton) {
            submitAnswer();
        } else if (e.getSource() == playAgainButton) {
            resetQuiz();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == optionA || e.getSource() == optionB || e.getSource() == optionC || e.getSource() == optionD) {
            JButton selectedButton = (JButton) e.getSource();
            selectedButton.setBackground(getContentPane().getBackground());
        }
    }

    private void startQuiz() {
        getContentPane().removeAll();

        questionLabel.setText(questions[currentQuestionIndex][0]);
        questionLabel.setForeground(Color.WHITE);

        optionA = new JRadioButton(questions[currentQuestionIndex][1]);
        optionB = new JRadioButton(questions[currentQuestionIndex][2]);
        optionC = new JRadioButton(questions[currentQuestionIndex][3]);
        optionD = new JRadioButton(questions[currentQuestionIndex][4]);

        optionGroup = new ButtonGroup();
        optionGroup.add(optionA);
        optionGroup.add(optionB);
        optionGroup.add(optionC);
        optionGroup.add(optionD);

        optionA.addActionListener(this);
        optionB.addActionListener(this);
        optionC.addActionListener(this);
        optionD.addActionListener(this);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionsPanel.add(optionA);
        optionsPanel.add(optionB);
        optionsPanel.add(optionC);
        optionsPanel.add(optionD);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(questionLabel, BorderLayout.NORTH);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        mainPanel.add(submitButton, BorderLayout.SOUTH);
        mainPanel.setBackground(colorMap.get(questions[currentQuestionIndex][5]));

        getContentPane().add(mainPanel);
        revalidate();
        repaint();
    }

    private void submitAnswer() {
        String selectedOption = getSelectedOption();
        String correctAnswer = questions[currentQuestionIndex][1];
        if (selectedOption.equals(correctAnswer)) {
            score++;
            JOptionPane.showMessageDialog(this, "Correct answer!", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Wrong answer! The correct answer is " + correctAnswer + ".", "Result", JOptionPane.ERROR_MESSAGE);
        }
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            startQuiz();
        } else {
            showResult();
        }
    }

    private String getSelectedOption() {
        if (optionA.isSelected()) {
            return optionA.getText();
        } else if (optionB.isSelected()) {
            return optionB.getText();
        } else if (optionC.isSelected()) {
            return optionC.getText();
        } else if (optionD.isSelected()) {
            return optionD.getText();
        } else {
            return "";
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "End of Quiz!\nYour score: " + score + "/" + questions.length, "Result", JOptionPane.INFORMATION_MESSAGE);
        removeCurrentPanel();
        JPanel resultPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(this);
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        resultPanel.add(playAgainButton);
        resultPanel.add(exitButton);
        resultPanel.setBackground(Color.WHITE);
        JOptionPane.showMessageDialog(this, resultPanel, "Quiz Result", JOptionPane.PLAIN_MESSAGE);
    }

    private void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        startQuiz();
    }

    private void removeCurrentPanel() {
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                getContentPane().remove(component);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MultipleChoiceQuiz());
    }
}
