package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Player {


    protected final Cards cards;

    protected Player(final Cards cards) {
        this.cards = cards;
    }

    public void receiveCard(final Card card) {
        cards.save(card);
    }

    public List<Card> showCards() {
        return List.copyOf(cards.getCards());
    }

    public int calculateResult() {
        return cards.calculateTotalPoint();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public abstract List<Card> openCards();

    public abstract boolean isReceivable();

}
