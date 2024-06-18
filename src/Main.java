import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PuzzleModel model = new PuzzleModel();
            PuzzleView view = new PuzzleView();
            PuzzleController controller = new PuzzleController(model, view);
            view.setVisible(true);
            controller.handleEvents();
        });
    }
}
