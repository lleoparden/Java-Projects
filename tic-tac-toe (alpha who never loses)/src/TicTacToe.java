import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TicTacToe extends JFrame {


    public final int SIZE = 3;
    public JButton[][] buttons = new JButton[SIZE][SIZE];
    public char[][] board = { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };
    public char player = 'X';  // Human is 'X', Alpha is 'O'
    public int plays = 0;
    public int it = 0;
    public int wins = 0;
    public int AlphaWins = 0;
    public int draws = 0;
    static Scanner scanner = new Scanner(System.in);


    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        // Initialize buttons
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton(String.valueOf(board[i][j]));
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    // Handle button clicks
    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().charAt(0) != 'X' && buttons[row][col].getText().charAt(0) != 'O') {
                buttons[row][col].setText("X");
                board[row][col] = 'X';
                plays++;

                if (isWinner('X')) {
                    JOptionPane.showMessageDialog(null, "Player X wins!","Impossible",JOptionPane.INFORMATION_MESSAGE);
                    resetBoard();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!","Alpha says:",JOptionPane.INFORMATION_MESSAGE);
                    draws++;
                    resetBoard();
                } else {
                    // After human player move, call Alpha move immediately
                    plays++;
                    alphaMove();


                    if (isWinner('O')) {
                        JOptionPane.showMessageDialog(null, "I win!","Alpha says:",JOptionPane.INFORMATION_MESSAGE);
                        AlphaWins++;
                        resetBoard();
                    } else if (isBoardFull()) {
                        JOptionPane.showMessageDialog(null, "It's a draw!","Alpha says:",JOptionPane.INFORMATION_MESSAGE);
                        draws++;
                        resetBoard();
                    }
                }
            }
        }
    }

    // Alpha Move (Alpha Move)
    public  boolean isWinner(char c) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == c && board[i][1] == c && board[i][2] == c) ||
                    (board[0][i] == c && board[1][i] == c && board[2][i] == c)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == c && board[1][1] == c && board[2][2] == c) ||
                (board[0][2] == c && board[1][1] == c && board[2][0] == c)) {
            return true;
        }
        return false;
    }

    public  boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public  boolean canWin(char c, int[] position) {
        wins = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == c && board[i][1] == c && board[i][2] != 'X' && board[i][2] != 'O') {
                position[0] = i;
                position[1] = 2;
                wins++;
            }
            if (board[i][0] == c && board[i][2] == c && board[i][1] != 'X' && board[i][1] != 'O') {
                position[0] = i;
                position[1] = 1;
                wins++;
            }
            if (board[i][1] == c && board[i][2] == c && board[i][0] != 'X' && board[i][0] != 'O') {
                position[0] = i;
                position[1] = 0;
                wins++;
            }
            if (board[0][i] == c && board[1][i] == c && board[2][i] != 'X' && board[2][i] != 'O') {
                position[0] = 2;
                position[1] = i;
                wins++;
            }
            if (board[0][i] == c && board[2][i] == c && board[1][i] != 'X' && board[1][i] != 'O') {
                position[0] = 1;
                position[1] = i;
                wins++;
            }
            if (board[1][i] == c && board[2][i] == c && board[0][i] != 'X' && board[0][i] != 'O') {
                position[0] = 0;
                position[1] = i;
                wins++;
            }
        }
        if (board[0][0] == c && board[1][1] == c && board[2][2] != 'X' && board[2][2] != 'O') {
            position[0] = 2;
            position[1] = 2;
            wins++;
        }
        if (board[0][0] == c && board[2][2] == c && board[1][1] != 'X' && board[1][1] != 'O') {
            position[0] = 1;
            position[1] = 1;
            wins++;
        }
        if (board[1][1] == c && board[2][2] == c && board[0][0] != 'X' && board[0][0] != 'O') {
            position[0] = 0;
            position[1] = 0;
            wins++;
        }
        if (board[0][2] == c && board[1][1] == c && board[2][0] != 'X' && board[2][0] != 'O') {
            position[0] = 2;
            position[1] = 0;
            wins++;
        }
        if (board[0][2] == c && board[2][0] == c && board[1][1] != 'X' && board[1][1] != 'O') {
            position[0] = 1;
            position[1] = 1;
            wins++;
        }
        if (board[1][1] == c && board[2][0] == c && board[0][2] != 'X' && board[0][2] != 'O') {
            position[0] = 0;
            position[1] = 2;
            wins++;
        }
        return wins >= 1;
    }

    public  boolean canWinNextRound(char c, char d) {
        int[] position = new int[2];
        char holder;
        char holder2;
        int i = 0;
        if (it == 1) {
            i = 1;
        }

        for (; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    holder = board[i][j];
                    board[i][j] = c;
                    buttons[i][j].setText(String.valueOf(c));

                    if (canWin(c, position)) {
                        if (c == 'O') {
                            holder2 = board[position[0]][position[1]];
                            board[i][j] = 'X';
                            buttons[i][j].setText("X");
                            boolean first = canWin('X', new int[2]);
                            int wins1 = wins;
                            board[i][j] = holder;
                            buttons[i][j].setText(String.valueOf(holder));
                            
                            board[position[0]][position[1]] = 'X';
                            buttons[position[0]][position[1]].setText("X");
                            boolean second = canWin('X', new int[2]);
                            int wins2 = wins;

                            if (first && second) {
                                if (wins1 > wins2) {
                                    board[i][j] = d;
                                    buttons[i][j].setText(String.valueOf(d));
                                    board[position[0]][position[1]] = holder2;
                                    buttons[position[0]][position[1]].setText(String.valueOf(holder2));
                                    return true;
                                } else {
                                    board[position[0]][position[1]] = d;
                                    buttons[position[0]][position[1]].setText(String.valueOf(d));
                                    board[i][j] = holder;
                                    buttons[i][j].setText(String.valueOf(holder2));
                                    return true;
                                }
                            } else if (first) {
                                board[i][j] = d;
                                buttons[i][j].setText(String.valueOf(d));
                                board[position[0]][position[1]] = holder2;
                                buttons[position[0]][position[1]].setText(String.valueOf(holder2));
                                return true;
                            } else if (second) {
                                board[position[0]][position[1]] = d;
                                buttons[position[0]][position[1]].setText(String.valueOf(d));
                                board[i][j] = holder;
                                buttons[i][j].setText(String.valueOf(holder2));
                                return true;
                            } else {
                                board[position[0]][position[1]] = holder2;
                                buttons[position[0]][position[1]].setText(String.valueOf(holder2));
                                board[i][j] = holder;
                                buttons[i][j].setText(String.valueOf(d));
                                continue;
                            }
                        } else {
                            board[i][j] = d;
                            buttons[i][j].setText(String.valueOf(d));
                            return true;
                        }
                    } else {
                        board[i][j] = holder;
                        buttons[i][j].setText(String.valueOf(holder));
                    }
                }
            }
        }
        return false;
    }

   

    public  void alphaMove() {
        int[] position = new int[2];

        if (canWin('O', position)) {
            board[position[0]][position[1]] = 'O';
            buttons[position[0]][position[1]].setText("O");
            return;
        }

        if (canWin('X', position)) {
            board[position[0]][position[1]] = 'O';
            buttons[position[0]][position[1]].setText("O");
            return;
        }

        if (plays == 2) {
            if (board[0][0] == 'X' || board[0][2] == 'X' || board[2][0] == 'X' || board[2][2] == 'X') {
                board[1][1] = 'O';
                buttons[1][1].setText("O");
                it = 1;
                return;
            }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == 'X') {
                        if (i != 1 || j != 1) {
                            if (i == 2) {
                                board[i - 1][j] = 'O';
                                buttons[i-1][j].setText("O");
                                return;
                            } else {
                                board[i + 1][j] = 'O';
                                buttons[i+1][j].setText("O");
                                return;
                            }
                        }
                    }
                }
            }
        }

        if (canWinNextRound('O', 'O')) {
            return;
        } else if (canWinNextRound('X', 'O')) {
            return;
        }

        if (board[1][1] != 'X' && board[1][1] != 'O') {
            board[1][1] = 'O';
            buttons[1][1].setText("O");
            return;
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    board[i][j] = 'O';
                    buttons[i][j].setText("O");
                    return;
                }
            }
        }

    }

    // Reset the board after a game
    private void resetBoard() {
        JOptionPane.showMessageDialog(null, "Number of times you won: 0 \nNumber of times Alpha won:" +AlphaWins +"\nNumber of Draws: "+draws,"results",JOptionPane.INFORMATION_MESSAGE);
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Number of times you won: 0");
        System.out.println("Number of times Alpha won: "+AlphaWins);
        System.out.println("Number of Draws: "+draws);
        plays = 0;
        it = 0;
        player = 'X'; // Reset to 'X'
        board = new char[][]{ {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} }; // Reset board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
