package domain.user;

import domain.card.Card;
import domain.card.Score;
import domain.dto.UserDto;

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

    public final boolean isName(Name playerName) {
        return getName().equals(playerName);
    }

    public final boolean isBust() {
        return userData.isBust();
    }

    public final boolean isBlackjack() {
        return userData.isBlackjack();
    }

    public final void doStay() {
        userData.doStay();
    }

    public final UserDto getUserDto() {
        return new UserDto(getName(), getScore(), getCards());
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

    public abstract int getPrize();

    protected UserData getUserData() {
        return userData;
    }
}
