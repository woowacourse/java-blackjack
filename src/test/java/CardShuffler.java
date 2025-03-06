import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardShuffler {

    public List<Card> shuffle(List<Card> deck) {
        List<Card> target = new ArrayList<>(deck);
        Collections.shuffle(target);
        return target;
    }
}
