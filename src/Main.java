public class Main {
    public static void main(String[] args) {
        PuzzleModel model = new PuzzleModel();
        PuzzleView view = new PuzzleView(model);
        PuzzleController controller = new PuzzleController(model, view);
       // controler.handleEvents();
        view.setVisible(true);
    }
    

}
