package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cardHand;

    public Cards(List<Card> cardHand) {
        this.cardHand = new ArrayList<>(cardHand);
    }
}
