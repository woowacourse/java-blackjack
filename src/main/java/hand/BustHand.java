package hand;

import static result.GameStatus.*;

import card.Card;
import java.util.List;
import result.GameStatus;

public class BustHand extends Hand{
    public BustHand(List<Card> cards) {
        super(cards);
    }

    @Override
    public GameStatus calculateResultAgainst(Hand other) {
        return LOSE;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
