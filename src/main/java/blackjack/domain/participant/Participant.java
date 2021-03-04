package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class Participant {

    public static final int STARTING_CARD_COUNT = 2;

    private final Hand hand;
    private final Name name;
    private boolean hit;

    public Participant(String name, Deck deck) {
        this.hand = new Hand(new ArrayList<>());
        this.hit = true;
        this.name = new Name(name);

        IntStream.range(0, STARTING_CARD_COUNT).forEach(i -> drawCard(deck));
    }

    abstract public void drawCard(Deck deck);

    public int getScore() {
        return hand.getScore();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isContinue() {
        return hit;
    }

    protected void cannotContinue() {
        this.hit = false;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public String getName() {
        return name.unwrap();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
