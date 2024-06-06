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

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                JButton b = buttons[row][col];
                b.setFont(FONT);
                b.setForeground(Color.RED);
                b.setBorderPainted(false);
                
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
        return null; // Should not happen if label is always valid
    }

}
