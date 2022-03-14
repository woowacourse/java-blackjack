package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.result.Outcome;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {

    private static final int TWENTY_ONE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final UserName name;
    private final Hand hand;

    public User(UserName name) {
        this.name = name;
        this.hand = new Hand(new ArrayList<>());
    }

    public void takeInitHand(Deck deck) {
        List<Card> newCards = deck.pickTwoCards();
        hand.add(newCards.get(0));
        hand.add(newCards.get(1));
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return getScore() > TWENTY_ONE;
    }

    public boolean isBlackjack() {
        if (getCardSize() == BLACKJACK_CARD_COUNT && getScore() == TWENTY_ONE) {
            return true;
        }
        return false;
    }

    private int getCardSize() {
        return hand.get().size();
    }

    public UserName getName() {
        return name;
    }

    public Hand getCards() {
        return hand;
    }

    public int getScore() {
        return hand.getTotalScore();
    }

    public abstract boolean isValidRange();

    public abstract Outcome determineWinner(User o);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(hand, user.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", hand=" + hand +
                '}';
    }
}
