package model.participant;

import static model.Blackjack.BLACKJACK_SCORE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Blackjack;
import model.card.Card;
import model.card.Cards;

public abstract class Participant {
    private final String name;
    protected final Cards hands;

    protected Participant(String name) {
        this.name = name;
        this.hands = Cards.from(new ArrayList<>());
    }

    public List<Card> receive(Card card) {
        hands.add(card);

        return hands.asList();
    }

    public boolean canHit() {
        return calculateScore() < BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return hands.size() == Blackjack.DEALOUT_DRAW_COUNT && calculateScore() == BLACKJACK_SCORE;
    }

    public int calculateScore() {
        return hands.calculateScore();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Participant that)) {
            return false;
        }
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public abstract List<Card> open();

    public abstract boolean beats(Participant participant);
}
