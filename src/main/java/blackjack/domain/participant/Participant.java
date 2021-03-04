package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class Participant {

    public static final int STARTING_CARD_COUNT = 2;
    private final Hand hand;
    private final String name;
    private boolean hit;

    public Participant(String name, Deck deck) {
        validateName(name);
        this.hand = new Hand(new ArrayList<>());
        this.hit = true;
        this.name = name;

        IntStream.range(0, STARTING_CARD_COUNT).forEach(i -> drawCard(deck));
    }

    private void validateName(String name) {
        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("공백은 이름으로 사용할 수 없습니다.");
        }
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

    public boolean isBust() {return hand.isBust();}

    public String getName() {
        return name;
    }

    protected void cannotDraw() {
        this.hit = false;
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
