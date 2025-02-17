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
                if (label.isEmpty()) {
                    emptyCell = new Tuple<>(row, col);
                }
                i++;
            }
        }
    }

    public String[][] getButtonLabels() {
        String[][] labels = new String[4][4];
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                labels[row][col] = buttons[row][col].getText();
            }
        }
        return labels;
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

        return (Math.abs(row1 - row2) == 1 && col1 == col2)
                || (Math.abs(col1 - col2) == 1 && row1 == row2);
    }

    public Map<Integer, Tuple<Integer, Integer>> getWinCoordinates() {
        Map<Integer, Tuple<Integer, Integer>> winCoords = new HashMap<>();
        for (int i = 1; i <= 16; i++) {
            winCoords.put(i, new Tuple<>((i - 1) / 4, (i - 1) % 4));
        }
        return winCoords;
    }

    public Tuple<Integer, Integer> getButtonPosition(String label) {
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                if (buttons[row][col].getText().equals(label)) {
                    return new Tuple<>(row, col);
                }
            }
        }
        return null;
    }

    public boolean isSolvable(List<String> puzzle) {
        int inversions = 0;
        int emptyRow = -1;

        for (int i = 0; i < puzzle.size(); i++) {
            if (puzzle.get(i).isEmpty()) {
                emptyRow = i / 4; // Get row of empty cell
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
        Map<Integer, Tuple<Integer, Integer>> winCoords = getWinCoordinates();

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                JButton button = buttons[row][col];

                int currentValue;
                try {
                    currentValue = Integer.parseInt(button.getText());
                } catch (NumberFormatException e) {
                    continue; // Skip empty buttons
                }

                Tuple<Integer, Integer> winPos = winCoords.get(currentValue);

                if (winPos != null && winPos.getFirst() == row && winPos.getSecond() == col) {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
