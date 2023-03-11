package domain.user;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public abstract class User {
    protected static final int BLACKJACK = 21;

//    private final List<Card> cards;
//    protected final Score score;
    protected Hand hand;

//    public User(List<Card> firstTurnCards) {
//        cards = new ArrayList<>(firstTurnCards);
//        score = new Score(firstTurnCards);
//    }

    public User(List<Card> firstTurnCards) {
        hand = new Hand(firstTurnCards);
    }

//    public void receiveCard(Card card) {
//        cards.add(card);
//        score.calculate(cards);
//        checkBustByScore();
//    }

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

    public abstract boolean isUserStatus(UserStatus status);

    protected abstract void checkBustByScore();

    public abstract String getName();
}
