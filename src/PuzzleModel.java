import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static Map<Integer, Tuple<Integer, Integer>> winCoordinates() {
        Map<Integer, Tuple<Integer, Integer>> m = new HashMap<>();
        m.put(1, new Tuple<>(1, 1));
        m.put(2, new Tuple<>(1, 2));
        m.put(3, new Tuple<>(1, 3));
        m.put(4, new Tuple<>(1, 4));
        m.put(5, new Tuple<>(2, 1));
        m.put(6, new Tuple<>(2, 2));
        m.put(7, new Tuple<>(2, 3));
        m.put(8, new Tuple<>(2, 4));
        m.put(9, new Tuple<>(3, 1));
        m.put(10, new Tuple<>(3, 2));
        m.put(11, new Tuple<>(3, 3));
        m.put(12, new Tuple<>(3, 4));
        m.put(13, new Tuple<>(4, 1));
        m.put(14, new Tuple<>(4, 2));
        m.put(15, new Tuple<>(4, 3));
        return m;

    }

}
