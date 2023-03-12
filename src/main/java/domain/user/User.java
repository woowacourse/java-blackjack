package domain.user;

import domain.card.Card;
import domain.card.Score;

import java.util.List;

public abstract class User {
    private final UserData userData;

    User(UserData userData) {
        this.userData = userData;
    }

    public final void receiveCards(List<Card> cards) {
        cards.forEach(this::receiveCard);
    }

    public final void receiveCard(Card card) {
        userData.receiveCard(card);
    }

    public final boolean hasResult() {
        return userData.hasResult();
    }

    public final List<Card> getCards() {
        return userData.getCards();
    }

    public final Score getScore() {
        return userData.getScore();
    }

    public final Name getName() {
        return userData.getName();
    }

    public final void endGame() {
        userData.doStay();
    }
}
