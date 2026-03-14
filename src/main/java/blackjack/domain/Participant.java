package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

abstract class Participant {
    protected final Name name;
    protected final Hand hand;

    protected Participant(Name name, Hand hand) {
        validate(name, hand);
        this.name = name;
        this.hand = hand;
    }

    private void validate(Name name, Hand hand) {
        Objects.requireNonNull(name, "참가자 이름은 null일 수 없습니다.");
        Objects.requireNonNull(hand, "참자가 덱(카드)은 null일 수 없습니다.");
    }

    public void hit(TrumpCard card) {
        hand.add(card);
    }

    public int score() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int countCards() {
        return hand.countCards();
    }

    public List<String> getCardNames() {
        return hand.cardNames();
    }

    public String getName() {
        return name.getName();
    }

    public abstract boolean canHit();

    public List<TrumpCard> getCards() {
        return hand.getCards();
    }
}
