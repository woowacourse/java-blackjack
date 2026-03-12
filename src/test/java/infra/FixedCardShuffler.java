package infra;

import domain.Card;
import domain.CardShuffler;
import java.util.ArrayList;
import java.util.List;

public class FixedCardShuffler implements CardShuffler {

    @Override
    public List<Card> shuffle(List<Card> cards) {
        return new ArrayList<>(cards);
    }

}