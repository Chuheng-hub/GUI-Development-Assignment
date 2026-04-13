import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterfaceApp extends JFrame {
    private JTextField nameField;
    private JButton startButton;
    private JButton attackButton;
    private JLabel statusLabel;

    private String playerName = "";
    private int playerHP = 100;
    private int enemyHP = 100;
    private boolean gameStarted = false;

    public UserInterfaceApp() {
        setTitle("Simple RPG GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top label
        statusLabel = new JLabel("Enter your name and start the game.", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        centerPanel.add(nameField);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        startButton = new JButton("Start Game");
        attackButton = new JButton("Attack");

        attackButton.setEnabled(false);

        buttonPanel.add(startButton);
        buttonPanel.add(attackButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Start button listener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText().trim();

                if (playerName.isEmpty()) {
                    statusLabel.setText("Name please!");
                } else {
                    gameStarted = true;
                    playerHP = 100;
                    enemyHP = 100;
                    attackButton.setEnabled(true);
                    statusLabel.setText("Welcome " + playerName + "! Player HP: " + playerHP + " | Enemy HP: " + enemyHP);
                }
            }
        });

        // Attack button listener
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameStarted) {
                    statusLabel.setText("Start the game first!");
                    return;
                }

                enemyHP -= 10;

                if (enemyHP <= 0) {
                    enemyHP = 0;
                    statusLabel.setText(playerName + " wins!");
                    attackButton.setEnabled(false);
                } else {
                    playerHP -= 8;

                    if (playerHP <= 0) {
                        playerHP = 0;
                        statusLabel.setText("Enemy wins!");
                        attackButton.setEnabled(false);
                    } else {
                        statusLabel.setText(playerName + " HP: " + playerHP + " | Enemy HP: " + enemyHP);
                    }
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new UserInterfaceApp();
    }
}
