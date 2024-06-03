import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PuzzleModel {

public static List<String> numShuffle() {
        List<String> numsList = new ArrayList<>();
        
        for (int i = 1; i <= 15; i++) {
            numsList.add(Integer.toString(i));
        }
        
        Collections.shuffle(numsList, new Random());

        return numsList;
    }


}
