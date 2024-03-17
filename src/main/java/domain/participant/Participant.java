package domain.participant;

import vo.Card;
import domain.card.Hand;
import vo.Name;

import java.util.Objects;

import static domain.BlackjackGame.BLACKJACK_SCORE;
import static domain.BlackjackGame.INITIAL_CARD_COUNT;

public abstract class Participant {

    private final Name name;
    private final Hand hand;

    Participant(final Name name) {
        this.name = name;
        hand = new Hand();
    }

    public abstract boolean canReceiveMoreCard();

    public void receive(final Card card) {
        hand.add(card);
    }

    public Card peekCard() {
        return hand.peek();
    }

    public int score() {
        return hand.score();
    }

    public boolean isBust() {
        return hand.score() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return hand.getCards()
                   .toList()
                   .size() == INITIAL_CARD_COUNT
                && hand.score() == BLACKJACK_SCORE;
    }

    public Name name() {
        return new Name(name);
    }

    public Hand hand() {
        return new Hand(hand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(hand, that.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }
}
