import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JButton;

public class PuzzleModel {
    private List<String> lShuffle;
    private JButton[][] buttons;
    private Tuple<Integer, Integer> emptyCell;
    private int moves = 0;

    public PuzzleModel() {
        lShuffle = numShuffle();
        buttons = new JButton[4][4];
        initializeButtons();
    }

    public int getMoves() {
        return moves;
    }

    public void restartGame() {
        lShuffle = numShuffle();
        initializeButtons();
        moves = 0;
    }

    public void initializeButtons() {
        do {
            lShuffle = numShuffle();
        } while (!isSolvable(lShuffle));

        int i = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                String label = (i < lShuffle.size()) ? lShuffle.get(i) : "";
                if (buttons[row][col] == null) {
                    buttons[row][col] = new JButton(label);
                } else {
                    buttons[row][col].setText(label);
                }
                if (label.isEmpty()) emptyCell = new Tuple<>(row, col);
                i++;
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public Tuple<Integer, Integer> getEmptyCellPosition() {
        return emptyCell;
    }

    public static List<String> numShuffle() {
        List<String> numsList = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            numsList.add(Integer.toString(i));
        }
        numsList.add("");
        Collections.shuffle(numsList, new Random());
        return numsList;
    }

    public void swapWithEmptyCell(Tuple<Integer, Integer> currentPosition) {
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
        moves++;
    }

    public boolean validSwap(Tuple<Integer, Integer> pos1, Tuple<Integer, Integer> pos2) {
        int row1 = pos1.getFirst();
        int col1 = pos1.getSecond();
        int row2 = pos2.getFirst();
        int col2 = pos2.getSecond();

        return (Math.abs(row1 - row2) == 1 && col1 == col2) ||
               (Math.abs(col1 - col2) == 1 && row1 == row2);
    }

    public static Map<Integer, Tuple<Integer, Integer>> getWinCoordinates() {
        Map<Integer, Tuple<Integer, Integer>> winCoords = new HashMap<>();
        winCoords.put(1, new Tuple<>(0, 0));
        winCoords.put(2, new Tuple<>(0, 1));
        winCoords.put(3, new Tuple<>(0, 2));
        winCoords.put(4, new Tuple<>(0, 3));
        winCoords.put(5, new Tuple<>(1, 0));
        winCoords.put(6, new Tuple<>(1, 1));
        winCoords.put(7, new Tuple<>(1, 2));
        winCoords.put(8, new Tuple<>(1, 3));
        winCoords.put(9, new Tuple<>(2, 0));
        winCoords.put(10, new Tuple<>(2, 1));
        winCoords.put(11, new Tuple<>(2, 2));
        winCoords.put(12, new Tuple<>(2, 3));
        winCoords.put(13, new Tuple<>(3, 0));
        winCoords.put(14, new Tuple<>(3, 1));
        winCoords.put(15, new Tuple<>(3, 2));
        winCoords.put(16, new Tuple<>(3, 3));
        return winCoords;
    }

    public static boolean isSolvable(List<String> puzzle) {
        int inversions = 0;
        int emptyRow = -1;

        for (int i = 0; i < puzzle.size(); i++) {
            if (puzzle.get(i).isEmpty()) {
                emptyRow = i / 4;
                continue;
            }

            for (int j = i + 1; j < puzzle.size(); j++) {
                if (!puzzle.get(j).isEmpty() && Integer.parseInt(puzzle.get(i)) > Integer.parseInt(puzzle.get(j))) {
                    inversions++;
                }
            }
        }

        return (inversions % 2 == 0) == (emptyRow % 2 != 0);
    }


    public boolean checkWin() {
        JButton[][] buttons = getButtons();
        Map<Integer, Tuple<Integer, Integer>> winCoords = getWinCoordinates();

        // Iterate over each button on the puzzle board
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                JButton button = buttons[row][col];

                int currentValue;
                try {
                    currentValue = Integer.parseInt(button.getText());
                } catch (NumberFormatException e) {
                    // If the button is empty, continue to the next button
                    continue;
                }

                Tuple<Integer, Integer> winPos = winCoords.get(currentValue);
                
                if (winPos != null && winPos.getFirst() == row && winPos.getSecond() == col) {
                    // If the button is in its winning position, continue to the next button
                    continue;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

}
