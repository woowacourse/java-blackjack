package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;

public abstract class User {

    private final UserDeck userDeck;

    public User(UserDeck userDeck) {
        this.userDeck = userDeck;
    }

    abstract boolean isAvailableDraw();

    public void draw(Card card) {
        userDeck.add(card);
    }

    public boolean isBustCondition() {
        return this.getScore() == UserDeck.BUST_CONDITION;
    }

    public UserDeck getUserDeck() {
        return userDeck;
    }

    public int getScore() {
        return userDeck.score();
    }

    public int compare(User user) {
        return this.getScore() - user.getScore();
    }
}
