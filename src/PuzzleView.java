import java.awt.*;
import java.util.Map;
import javax.swing.*;

public class PuzzleView extends JFrame {
    private static final Font FONT = new Font("Eurostile", Font.BOLD, 28);
    private JTextField display;
    private JButton restartButton;
    private JPanel tastenpanel;
    private JLabel winImageLabel;
    private JButton[][] buttons;

    public PuzzleView() {
        super("Puzzle Spiel");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Click X to close window

        // Create a display field
        display = new JTextField();
        display.setEditable(false);
        display.setForeground(Color.BLACK);
        display.setBackground(Color.LIGHT_GRAY);
        display.setFont(FONT);
        display.setHorizontalAlignment(JTextField.CENTER);

        tastenpanel = new JPanel(new GridLayout(4, 4, 6, 6));

        // Restart button
        restartButton = new JButton("New Game");
        restartButton.setFocusable(false);
        restartButton.setBackground(Color.RED); // Set restart button color to red
        restartButton.setForeground(Color.BLACK); // Set restart button text to white
        restartButton.setFont(new Font("Arial", Font.BOLD, 16));

        buttons = new JButton[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(FONT);
                buttons[row][col].setFocusable(false);
                buttons[row][col].setBackground(Color.LIGHT_GRAY); // Set default button color
                buttons[row][col].setForeground(Color.BLACK); // Set default button text color
                tastenpanel.add(buttons[row][col]);
            }
        }

        // Main panel layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(display, BorderLayout.NORTH);
        panel.add(tastenpanel, BorderLayout.CENTER);
        panel.add(restartButton, BorderLayout.SOUTH);
        this.add(panel);
        this.setVisible(true);

        // Win image label
        winImageLabel = new JLabel();
        winImageLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(winImageLabel, BorderLayout.NORTH);
    }

    public void updateView(PuzzleModel model) {
        String[][] labels = model.getButtonLabels();
        Map<Integer, Tuple<Integer, Integer>> winCoords = model.getWinCoordinates(); // Get winning coordinates
        
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setText(labels[row][col]);

                // Determine if the button is in the correct position
                if (!labels[row][col].isEmpty()) {
                    int currentValue = Integer.parseInt(labels[row][col]);
                    Tuple<Integer, Integer> correctPosition = winCoords.get(currentValue);

                    // Set button colors based on position correctness
                    if (correctPosition != null && correctPosition.getFirst() == row && correctPosition.getSecond() == col) {
                        buttons[row][col].setForeground(Color.GREEN); // Correct position
                    } else {
                        buttons[row][col].setForeground(Color.RED); // Incorrect position
                    }
                }
                
                // Set the empty button's background color
                if (labels[row][col].isEmpty()) {
                    buttons[row][col].setBackground(Color.LIGHT_GRAY); // Color for empty button
                } else {
                    buttons[row][col].setBackground(Color.RED); // Normal button color for others
                }
            }
        }

        display.setText("Moves: " + model.getMoves());
        if (model.checkWin()) {
            display.setText("You win! Moves: " + model.getMoves());
            winImageLabel.setText("🎉 You Win! 🎉");
            winImageLabel.setFont(new Font("Arial", Font.BOLD, 20));
            winImageLabel.setForeground(Color.GREEN);
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public void clearWinImage() {
        winImageLabel.setText("");
    }
}
