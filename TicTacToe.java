import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JLabel statusLabel;
    private JPanel panel;
    private JLabel resultLabel;

    private boolean xTurn = true;
    private boolean gameEnded = false;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        statusLabel = new JLabel("X's turn");
        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 30));

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].setForeground(Color.WHITE);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }

        JPanel statusPanel = new JPanel();
        statusPanel.add(statusLabel);

        JPanel resultPanel = new JPanel();
        resultPanel.add(resultLabel);

        add(panel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (gameEnded)
            return;

        JButton buttonClicked = (JButton) e.getSource();
        if (!buttonClicked.getText().equals(""))
            return;

        if (xTurn) {
            buttonClicked.setText("X");
            buttonClicked.setForeground(Color.RED);
        } else {
            buttonClicked.setText("O");
            buttonClicked.setForeground(Color.BLUE);
        }

        xTurn = !xTurn;

        if (checkWinner()) {
            if (xTurn)
                resultLabel.setText("O wins!");
            else
                resultLabel.setText("X wins!");
            gameEnded = true;
        } else if (isBoardFull()) {
            resultLabel.setText("It's a draw!");
            gameEnded = true;
        } else {
            if (xTurn)
                statusLabel.setText("X's turn");
            else
                statusLabel.setText("O's turn");
        }
    }

    private boolean checkWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j].equals(board[1][j]) && board[0][j].equals(board[2][j]) && !board[0][j].equals("")) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
