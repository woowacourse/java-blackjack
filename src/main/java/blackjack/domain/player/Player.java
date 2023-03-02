package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Card> holdingCards = new ArrayList<>();

    public void getInitialCards(List<Card> cards) {
        for (Card card : cards) {
            holdingCards.add(card);
        }
    }

    public List<Card> getHoldingCards() {
        return List.copyOf(holdingCards);
    }

    public void pick(Card card) {
        holdingCards.add(card);
    }
}
