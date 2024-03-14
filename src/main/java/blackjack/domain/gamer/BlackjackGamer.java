package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BlackjackGamer {

    private final Name name;
    private final Hand hand;

    public BlackjackGamer(String name, List<Card> cards) {
        this.name = new Name(name);
        this.hand = new Hand(cards);
    }

    public BlackjackGamer(String name) {
        this(name, new ArrayList<>());
    }

    public abstract boolean canReceiveCard();

    public void initCard(Deck deck, int initialCardCount) {
        for (int i = 0; i < initialCardCount; i++) {
            addCard(deck.draw());
        }
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public boolean isBusted() {
        return hand.checkIfBust();
    }

    public boolean isBlackjack() {
        return hand.checkIfBlackjack();
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlackjackGamer that = (BlackjackGamer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
