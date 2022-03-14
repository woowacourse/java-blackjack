package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.deck.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {

    private static final int TWENTY_ONE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final UserName name;
    private final Cards cards;

    public User(UserName name) {
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public void takeInitHand(Deck deck) {
        List<Card> newCards = deck.pickTwoCards();
        cards.add(newCards.get(0));
        cards.add(newCards.get(1));
    }

    public void hit(Card card) {
        cards.add(card);
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
        return cards.get().size();
    }

    public UserName getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public int getScore() {
        return cards.getTotalScore();
    }

    public abstract boolean isValidRange();

    public abstract int compare(User o);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(cards, user.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
