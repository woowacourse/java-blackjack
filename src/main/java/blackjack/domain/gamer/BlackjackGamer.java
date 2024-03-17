package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class BlackjackGamer {

    final Hand hand;

    public BlackjackGamer(List<Card> cards) {
        this.hand = new Hand(cards);
    }

    public BlackjackGamer() {
        this(new ArrayList<>());
    }

    public abstract boolean canReceiveCard();

    public abstract String getName();

    public void addCard(Card card) {
        hand.add(card);
    }

    public boolean isBusted() {
        return hand.checkIfBust();
    }

    public boolean isBlackjack() {
        return hand.checkIfBlackjack();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public List<Card> getCurrentCards() {
        return hand.cards();
    }
}
