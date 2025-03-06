package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Gambler {
    public Dealer(Name name) {
        super(name);
    }

    public List<Card> getInitialCards() {
        List<Card> cards = hands.getCards();
        Card firstCard = cards.getFirst();
        return List.of(firstCard);
    }
}
