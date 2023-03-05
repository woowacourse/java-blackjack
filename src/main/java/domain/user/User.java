package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected static final int BLACKJACK = 21;

    private final List<Card> cards;
    protected final Score score;

    public User() {
        cards = new ArrayList<>();
        score = new Score();
    }

    public void receiveCard(Card card) {
        cards.add(card);
        score.calculate(cards);
        checkBustByScore();
    }

    public void receiveCards(List<Card> receivedCards) {
        cards.addAll(receivedCards);
        score.calculate(cards);
        checkBustByScore();
    }

    protected abstract void checkBustByScore();

    public abstract void win(User opponent);

    public abstract void lose();

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score.getScore();
    }

    public abstract UserStatus getStatus();

    public abstract String getName();
}
