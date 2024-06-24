import javax.swing.*;

public class PuzzleController {
    private PuzzleModel model;
    private PuzzleView view;

    public PuzzleController(PuzzleModel model, PuzzleView view) {
        this.model = model;
        this.view = view;
        handleEvents();
        view.updateView(model);
    }

    public void handleEvents() {
        JButton[][] buttons = this.view.getButtons();

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                JButton button = buttons[row][col];

                button.addActionListener(ev -> {
                    String label = ((JButton) ev.getSource()).getText();

                    Tuple<Integer, Integer> currentPosition = model.getButtonPosition(label);
                    Tuple<Integer, Integer> emptyPosition = model.getEmptyCellPosition();

                    if (model.validSwap(currentPosition, emptyPosition)) {
                        model.swapWithEmptyCell(currentPosition);
                        view.updateView(model);
                    }

                    System.out.println("Button pressed: " + label + " at coordinate: " + currentPosition); // for debug
                    System.out.println("Empty cell at coordinate: " + emptyPosition);
                });
            }
        }

        view.getRestartButton().addActionListener(ev -> {
            model.restartGame();
            view.updateView(model);
            view.clearWinImage();
        });
    }

    
}
