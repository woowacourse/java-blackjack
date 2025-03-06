package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Gambler {
    public List<Card> getInitialCards() {
        List<Card> cards = hands.getCards();
        Card firstCard = cards.getFirst();
        return List.of(firstCard);
    }

    public boolean mustDraw() {
        return hands.isScoreBelow(16);
    }
}
