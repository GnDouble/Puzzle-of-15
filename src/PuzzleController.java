import javax.swing.*;

public class PuzzleController {
    private PuzzleModel model;
    private PuzzleView view;

    public PuzzleController(PuzzleModel model, PuzzleView view) {
        this.model = model;
        this.view = view;
    }

    public void handleEvents() {
        JButton[][] buttons = this.model.getButtons();

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                JButton button = buttons[row][col];
                if (button != null && !button.getText().isEmpty()) {
                    button.addActionListener(ev -> {
                        String label = ((JButton) ev.getSource()).getText();
                        Tuple<Integer, Integer> currentPosition = view.getButtonPosition(label);
                        Tuple<Integer, Integer> emptyPosition = model.getEmptyCellPosition();
                        System.out.println("Button pressed: " + label + " at coordinate: " + currentPosition);
                        System.out.println("Empty cell at coordinate: " + emptyPosition);
            
                    });
                }
            }
        }
    }
}
