package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private final List<Card> cards;
    protected final Score score;

    public User() {
        cards = new ArrayList<>();
        score = new Score();
    }

    public void receiveCard(Card card) {
        cards.add(card);
        score.setScore(cards);
    }

    public void receiveCards(List<Card> receivedCards) {
        cards.addAll(receivedCards);
        score.setScore(cards);
    }

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
