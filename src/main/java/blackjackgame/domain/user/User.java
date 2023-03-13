package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import java.util.List;

public abstract class User {
    private final Hands hands;
    private final Name name;
    protected final Score score;

    public User(Name name) {
        this.hands = new Hands();
        this.score = new Score();
        this.name = name;
    }

    public void receiveCard(Card card) {
        hands.add(card);
        score.setScore(hands);
    }

    public void receiveCards(List<Card> cards) {
        hands.add(cards);
        score.setScore(hands);
    }

    public boolean isLessThanBustScore() {
        return score.isLessThanBustScore();
    }

    public List<Card> getCards() {
        return hands.getCards();
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return score.getScore();
    }

    public abstract UserStatus getStatus();
}
