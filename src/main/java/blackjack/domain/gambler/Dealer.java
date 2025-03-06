package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Gambler {
    public List<Card> getInitialCard() {
        List<Card> cards = hands.getCards();
        Card firstCard = cards.getFirst();
        return List.of(firstCard);
    }
}
