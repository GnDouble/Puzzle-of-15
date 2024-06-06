import javax.swing.*;
import java.awt.*;

public class PuzzleView extends JFrame {
    private PuzzleModel model;
    private static final Font FONT = new Font("Eurostile", Font.BOLD, 28);

    public PuzzleView(PuzzleModel model) {
        super("Puzzle Spiel");
        this.model = model;
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel tastenpanel = new JPanel(new GridLayout(4, 4, 6, 6));
        JButton[][] buttons = model.getButtons();

        for (JButton[] buttonRow : buttons) {
            for (JButton b : buttonRow) {
                b.setFont(FONT);
                b.setForeground(Color.RED);
                tastenpanel.add(b);
            }
        }

        this.add(tastenpanel, BorderLayout.CENTER);
    }

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
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setText(buttons[row][col].getText());
            }
        }
        this.revalidate();
        this.repaint();
    }
}
