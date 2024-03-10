package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.result.Score;
import java.util.ArrayList;
import java.util.Objects;

public class Participant {
    private final Hands hands;
    private final ParticipantName name;

    private Participant(final String name) {
        this.name = new ParticipantName(name);
        this.hands = new Hands(new ArrayList<>());
    }

    public static Participant from(final String name) {
        return new Participant(name);
    }

    public void addCard(final Card card) {
        hands.add(card);
    }

    public Score calculateScore() {
        return hands.calculateScore();
    }

    public boolean isName(final String name) {
        return this.name.is(name);
    }

    public boolean isNotBlackjack() {
        return hands.isNotBlackjack();
    }

    public boolean isNotDead() {
        return hands.isNotDead();
    }


    public Hands getHands() {
        return new Hands(hands.getCards());
    }

    public Hands getFirstCard() {
        return hands.getFirstCard();
    }

    public ParticipantName getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Participant that)) {
            return false;
        }
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
