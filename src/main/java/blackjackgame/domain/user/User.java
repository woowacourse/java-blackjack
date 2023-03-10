package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private final List<Card> cards;
    private final Name name;
    protected final Score score;

    public User(Name name) {
        this.cards = new ArrayList<>();
        this.score = new Score();
        this.name = name;
    }

    public void receiveCard(Card card) {
        cards.add(card);
        score.setScore(cards);
    }

    public void receiveCards(List<Card> receivedCards) {
        cards.addAll(receivedCards);
        score.setScore(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return score.getScore();
    }

    public abstract UserStatus getStatus();
}
