import javax.swing.*;
import java.awt.*;
import java.util.Map;

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

        display = new JTextField();
        display.setEditable(false);
        display.setForeground(Color.BLACK);

        tastenpanel = new JPanel(new GridLayout(4, 4, 6, 6));

        restartButton = new JButton("New Game");
        restartButton.setFont(FONT);
        restartButton.setForeground(Color.BLUE);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(display, BorderLayout.CENTER);
        bottomPanel.add(restartButton, BorderLayout.EAST);

        winImageLabel = new JLabel();
        bottomPanel.add(winImageLabel, BorderLayout.NORTH); // Add winImageLabel to bottomPanel

        this.add(tastenpanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        buttons = new JButton[4][4]; // Initialize button array
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(FONT);
                buttons[row][col].setForeground(Color.RED);
                tastenpanel.add(buttons[row][col]);
            }
        }
    }


    public void updateButtons(String[][] buttonLabels) {
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setText(buttonLabels[row][col]);
            }
        }
    }

    public void updateButtonColors(Map<Integer, Tuple<Integer, Integer>> winCoords) {
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                String label = buttons[row][col].getText();
                if (label.isEmpty()) continue;

                int currentValue = Integer.parseInt(label);
                Tuple<Integer, Integer> winPos = winCoords.get(currentValue);
                if (winPos != null && winPos.getFirst() == row && winPos.getSecond() == col) {
                    buttons[row][col].setForeground(Color.GREEN);
                } else {
                    buttons[row][col].setForeground(Color.RED);
                }
            }
        }
    }

    public void showWinImage() {
        ImageIcon winImageIcon = new ImageIcon("/Users/mgenius/VS Proj/GUI Neu/PuzzleGame/src/image/gettyimages-1255091358-2048x2048 2.jpg");
        winImageLabel.setIcon(winImageIcon);
    }

    public void clearWinImage() {
        winImageLabel.setIcon(null);
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public JButton[][] getButtons() {
        return buttons;
    }
    public void updateMoves(int moves) {
        display.setText("Moves: " + moves);
    }

    public void updateView(PuzzleModel model) {
        updateButtons(model.getButtonLabels());
        updateMoves(model.getMoves());
        updateButtonColors(model.getWinCoordinates());
    
        if (model.checkWin()) {
            System.out.println("Player has won!");
            showWinImage();
        } 
    }
}
