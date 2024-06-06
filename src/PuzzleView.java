import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.awt.event.ActionListener; 


public class PuzzleView extends JFrame {
    private PuzzleModel model;
    private static final Font FONT = new Font("Eurostile", Font.BOLD, 28);
    private JTextField display;
    private JButton restartButton;

    public PuzzleView(PuzzleModel model) {
        super("Puzzle Spiel");
        this.model = model;
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Click X to close window

        display = new JTextField();
        display.setEditable(false);
        display.setText("Moves: " + model.getMoves()); // create Display for Moves BorderLayout.South
        display.setForeground(Color.BLACK);

        JPanel tastenpanel = new JPanel(new GridLayout(4, 4, 6, 6));
        JButton[][] buttons = model.getButtons();

        // Initialize buttons
        for (JButton[] buttonRow : buttons) {
            for (JButton b : buttonRow) {
                b.setFont(FONT);
                b.setForeground(Color.RED);
                tastenpanel.add(b);
            }
        }

        restartButton = new JButton("Restart");
        restartButton.setFont(FONT);
        restartButton.setForeground(Color.BLUE);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(display, BorderLayout.CENTER);
        bottomPanel.add(restartButton, BorderLayout.EAST);

        this.add(tastenpanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    

    // get current Button Positions
    public Tuple<Integer, Integer> getButtonPosition(String label) {
        JButton[][] buttons = model.getButtons();
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                if (buttons[row][col].getText().equals(label)) {
                    return new Tuple<>(row, col);
                }
            }
        }
        return null;
    }

    public void updateView() {
        JButton[][] buttons = model.getButtons();

        // Get the winning coordinates from the model
        Map<Integer, Tuple<Integer, Integer>> winCoords = model.getWinCoordinates();

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                JButton button = buttons[row][col];

                // Get the value of the current button
                int currentValue;
                try {
                    currentValue = Integer.parseInt(button.getText());
                } catch (NumberFormatException e) {
                    // If the button is empty skip to the next button
                    continue;
                }

                // Check if the current button is at the correct position with use of Map from Model
                Tuple<Integer, Integer> winPos = winCoords.get(currentValue);
                if (winPos != null && winPos.getFirst() == row && winPos.getSecond() == col) {
                    button.setForeground(Color.GREEN);
                } else {
                    button.setForeground(Color.RED);
                }
            }
        }

        display.setText("Moves: " + model.getMoves()); // Update Display for Movesa
    }
    public JButton getRestartButton() {
        return restartButton;
    }
}
