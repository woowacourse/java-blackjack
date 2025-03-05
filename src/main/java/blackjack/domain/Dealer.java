package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Gambler {
    public Card getInitialCard() {
        List<Card> cards = hands.getCards();
        return cards.getFirst();
    }
}
