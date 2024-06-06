// PuzzleController.java
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PuzzleController {
    private PuzzleModel model;
    private PuzzleView view;

    public PuzzleController(PuzzleModel model, PuzzleView view) {
        this.model = model;
        this.view = view;
        view.updateView();
        
        handleEvents();
    }

    public void handleEvents() {
        JButton[][] buttons = this.model.getButtons();

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                JButton button = buttons[row][col];

                button.addActionListener(ev -> {
                    String label = ((JButton) ev.getSource()).getText();

                    Tuple<Integer, Integer> currentPosition = view.getButtonPosition(label);
                    Tuple<Integer, Integer> emptyPosition = model.getEmptyCellPosition();

                    if (model.validSwap(currentPosition, emptyPosition)) { //Checking if is possible to swap
                        model.swapWithEmptyCell(currentPosition); //Swapping coordinates
                        view.updateView(); 
                    }

                    System.out.println("Button pressed: " + label + " at coordinate: " + currentPosition); // for debug
                    System.out.println("Empty cell at coordinate: " + emptyPosition);
                });
            
            }
        }
        //view.getRestartButton().addActionListener(this::model.restartGame();
    }

    
}
