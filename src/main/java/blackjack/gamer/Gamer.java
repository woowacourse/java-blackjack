package blackjack.gamer;

import blackjack.card.Card;
import blackjack.card.Hand;
import java.util.List;

public abstract class Gamer {

    protected final Hand hand;

    public Gamer() {
        this.hand = new Hand();
    }

    public void receiveCards(List<Card> cards) {
        hand.addCards(cards);
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public List<Card> showAllCards() {
        return hand.openAllCards();
    }

    public int sumCards() {
        return hand.sumCards();
    }

    public boolean isBust(final int threshold) {
        return sumCards() > threshold;
    }

    public boolean isBlackjack(final int blackjackNumber, final int blackjackCardCount) {
        return hand.sumCards() == blackjackNumber && hand.countCards() == blackjackCardCount;
    }

    public abstract List<Card> showInitialCards();

    public abstract String getNickName();
}
