import java.util.List;
import java.util.Map;

import javax.swing.*;

public class PuzzleController {
    private PuzzleModel model;
    private PuzzleView view;

    public PuzzleController (PuzzleModel m, PuzzleView v) {
        this.model = m;
        this.view = v;
    }

    public void handleEvents() {
        Map<Integer, Tuple<Integer, Integer>> winCoordinates = PuzzleModel.winCoordinates();

        this.view.getButtons().forEach(button -> button.addActionListener(ev -> {
            String label = ((JButton) ev.getSource()).getText();
            Integer buttonNumber = Integer.parseInt(label);
            Tuple<Integer, Integer> coordinate = winCoordinates.get(buttonNumber);

            System.out.println("Button " + label + " clicked. Coordinate: (" + coordinate.getFirst() + ", " + coordinate.getSecond() + ")");
        }));
    }
}
