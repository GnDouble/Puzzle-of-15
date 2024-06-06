import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;

public class PuzzleModel {
    private List<String> lShuffle;
    private JButton[][] buttons;
    private Tuple emptyCell;

    public PuzzleModel() {
        lShuffle = numShuffle();
        buttons = new JButton[4][4];
        initializeButtons();
        findEmptyCell();
    }

    private void initializeButtons() {
        int i = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                String label = (i < lShuffle.size()) ? lShuffle.get(i) : "";
                JButton b = new JButton(label);
                buttons[row][col] = b;
                i++;
            }
        }
    }

    private void findEmptyCell() {
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    emptyCell = new Tuple(row, col);
                    return;
                }
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public Tuple getEmptyCellPosition() {
        return emptyCell;
    }

    public static List<String> numShuffle() {
        List<String> numsList = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            numsList.add(Integer.toString(i));
        }
        numsList.add("");
        Collections.shuffle(numsList, new Random());
        return numsList;
    }

    public void swapWithEmptyCell(Tuple currentPosition) {
        int currentRow = currentPosition.getFirst();
        int currentCol = currentPosition.getSecond();

        int emptyRow = emptyCell.getFirst();
        int emptyCol = emptyCell.getSecond();

        // Swap texts of the buttons
        String tempText = buttons[currentRow][currentCol].getText();
        buttons[currentRow][currentCol].setText(buttons[emptyRow][emptyCol].getText());
        buttons[emptyRow][emptyCol].setText(tempText);

        // Update the empty cell position
        emptyCell = currentPosition;
    }

    public static boolean validSwap(Tuple pos1, Tuple pos2) {
        int row1 = pos1.getFirst();
        int col1 = pos1.getSecond();
        int row2 = pos2.getFirst();
        int col2 = pos2.getSecond();

        return (Math.abs(row1 - row2) == 1 && col1 == col2) ||
               (Math.abs(col1 - col2) == 1 && row1 == row2);
    }

    
}



