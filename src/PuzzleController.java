import javax.swing.*;

public class PuzzleController {
    private PuzzleModel pM;
    private PuzzleView pV;

    public PuzzleController (PuzzleModel m, PuzzleView v) {
        this.pM = m;
        this.pV = v;
    }

    public void handleEvents() {
        this.pV.getButtons().forEach(b -> b.addActionListener(ev -> {
            String label = ((JButton) ev.getSource()).getText();
            if ("123456789101112131415".contains(label)){
                Integer pos = m.getPos();
            }

            pV.show(pM);
        }));
        
        
    }
}
