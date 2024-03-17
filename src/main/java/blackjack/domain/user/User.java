package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public abstract class User {

    private final UserName userName;
    private Hands hands;

    protected User(UserName userName) {
        this.userName = userName;
        this.hands = new Hands();
    }

    public abstract boolean canHit();

    public void draw(Card card) {
        hands = hands.addCard(card);
    }

    public UserName getUserName() {
        return userName;
    }

    public Hands getHands() {
        return hands;
    }
}
