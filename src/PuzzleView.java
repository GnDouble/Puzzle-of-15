import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleView extends JFrame {
    private PuzzleModel m;
    private static final Font FONT = new Font("Eurostile", Font.BOLD, 28);
    private List<JButton> buttons = new ArrayList<>();
    private List<String> lShuffle = PuzzleModel.numShuffle();

    public PuzzleView(PuzzleModel e) {

        super("Puzzle Spiel");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel tastenpanel = new JPanel(new GridLayout(4, 4, 6, 6));
        JButton b = null;
        for (String label : lShuffle) {
            b = new JButton(label);

            b.setFont(FONT);
            b.setForeground(new Color(212, 175, 55));

            b.setBorderPainted(false);
            buttons.add(b);
            tastenpanel.add(b);
        }

        this.add(tastenpanel, BorderLayout.CENTER);
    }

    public List<JButton> getButtons() {
        return buttons;
    }

    // public void show(PuzzleModel m) {
    // this.display.setText(m.toString());
    // }

}
