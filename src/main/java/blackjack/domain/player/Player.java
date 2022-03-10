package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Player {

    protected final Cards cards;

    public Player(Cards cards) {
        this.cards = cards;
    }

    public void receiveCard(final Card card) {
        cards.save(card);
    }

    public abstract List<Card> openCards();

    public List<Card> showCards() {
        return List.copyOf(cards.getCards());
    }

    public int calculateResult() {
        return cards.calculateTotalPoint();
    }

    public abstract boolean isSatisfyReceiveCondition();

    public abstract String getName();
}
