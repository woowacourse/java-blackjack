package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Gambler {
    public Dealer(final Name name) {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        List<Card> cards = getCards();
        Card firstCard = cards.getFirst();
        return List.of(firstCard);
    }
}
