package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Gambler {
    public static final Name DEALER_NAME = new Name("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public List<Card> getInitialCards() {
        List<Card> cards = getCards();
        Card firstCard = cards.getFirst();
        return List.of(firstCard);
    }
}
