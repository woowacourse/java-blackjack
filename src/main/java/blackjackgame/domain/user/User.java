package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import java.util.List;

public abstract class User {
    protected final Hands hands;
    private final Name name;

    public User(Name name) {
        this.hands = new Hands();
        this.name = name;
    }

    public void receiveCard(Card card) {
        hands.add(card);
    }

    public void receiveCards(List<Card> cards) {
        hands.add(cards);
    }

    public boolean isLessThanBustScore() {
        return hands.isLessThanBustScore();
    }

    public List<Card> getCards() {
        return hands.getCards();
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return hands.getScore();
    }

    public abstract UserStatus getStatus();
}
