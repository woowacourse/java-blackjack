package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public abstract class Player {

    protected final HoldingCards holdingCards;

    protected Player() {
        this.holdingCards = new HoldingCards();
    }

    public void pickStartCards(List<Card> cards) {
        holdingCards.initialCard(cards);
    }

    public HoldingCards getHoldingCards() {
        return holdingCards;
    }

    public void pick(Card card) {
        holdingCards.add(card);
    }

    public abstract Boolean canPick();
}
