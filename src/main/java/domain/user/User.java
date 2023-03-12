package domain.user;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;
import domain.state.State;

import java.util.List;

public abstract class User {
    protected Hand hand;

    public User(List<Card> firstTurnCards) {
        hand = new Hand(firstTurnCards);
    }

    public void receiveCard(Card card) {
        hand = hand.add(card);
        checkBustByScore();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public abstract boolean isUserStatus(State status);

    protected abstract void checkBustByScore();

    public abstract String getName();
}
