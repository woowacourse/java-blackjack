package blackjack.domain.participant;

import blackjack.domain.result.BlackjackStatus;
import blackjack.domain.result.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
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

    public Score calculate() {
        int sum = hands.sum();
        int aceCount = hands.countAce();

        while (isDeadScore(sum) && aceCount-- > 0) {
            sum -= 10;
        }

        return new Score(sum);
    }

    private boolean isDeadScore(final int sum) {
        return BlackjackStatus.from(new Score(sum)).isDead();
    }

    public boolean isName(final String name) {
        return this.name.is(name);
    }

    public boolean isNotBlackjack() {
        return !getStatus().isBlackjack();
    }

    public boolean isNotDead() {
        return !getStatus().isDead();
    }

    private BlackjackStatus getStatus() {
        return BlackjackStatus.from(calculate());
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
