import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleView extends JFrame {
    private static final Font FONT = new Font("Eurostile", Font.BOLD, 28);
    private List<JButton> buttons = new ArrayList<>();

    public PuzzleView() {
        super("Puzzle Spiel");
        this.setSize(350, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel tastenpanel = new JPanel(new GridLayout(4, 4, 1, 1));
        String[] intArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "15", "14"};

        for (int i = 0; i < 16; i++) {
            if (i < intArray.length) {
                JButton b = new JButton(intArray[i]);
                b.setFont(FONT);
                b.setForeground(new Color(212, 175, 55));

                int row = i / 4;
                int col = i % 4;

                if ((row + col) % 2 == 0) {
                    b.setBackground(Color.RED);
                } else {
                    b.setBackground(Color.WHITE);
                }

                b.setOpaque(true);
                b.setBorderPainted(false);
                buttons.add(b);
                tastenpanel.add(b);
            } else {
                JLabel emptyLabel = new JLabel();
                tastenpanel.add(emptyLabel);
            }
        }

        this.add(tastenpanel, BorderLayout.CENTER);
    }
    public List<JButton> getButtons() { return buttons; }


}
