package domain.participant;

import domain.Hands;
import domain.Result;
import domain.card.Card;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final String name;
    private final Hands hands;

    protected Participant(final String name, final Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public void add(final Card card) {
        hands.add(card);
    }

    public boolean isBust() {
        return hands.isBust();
    }

    public boolean isBlackJack() {
        return hands.isBlackJack();
    }

    public int handsSum() {
        return hands.sum();
    }

    public int handsSize() {
        return hands.size();
    }

    public Result calculateResult(final Participant participant) {
        return hands.calculateResult(participant.getHands());
    }

    public List<String> getCardNames() {
        return hands.getCards();
    }

    public String getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Participant participant)) {
            return false;
        }
        return Objects.equals(name, participant.name) && Objects.equals(hands, participant.hands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hands);
    }
}
