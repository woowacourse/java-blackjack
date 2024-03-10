package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.result.Score;
import java.util.ArrayList;

public class Participant {
    private final Hands hands;
    private final ParticipantName name;

    protected Participant(final String name) {
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
}
