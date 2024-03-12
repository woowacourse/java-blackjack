package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.result.BlackjackStatus;
import blackjack.domain.result.Score;
import java.util.ArrayList;

public class Participant {
    protected final ParticipantName name;
    protected final Hands hands;

    protected Participant(final String name) {
        this.name = new ParticipantName(name);
        this.hands = new Hands(new ArrayList<>());
    }

    public Score calculate() {
        int sum = hands.sum();
        int aceCount = hands.countAce();

        while (sum > BlackjackStatus.BLACKJACK_NUMBER && aceCount > 0) {
            sum = sum - 10;
            aceCount--;
        }
        return new Score(sum);
    }

    public void addCard(final Card card) {
        hands.add(card);
    }

    protected BlackjackStatus getStatus() {
        return BlackjackStatus.from(calculate());
    }

    public boolean isNotBlackjack() {
        return getStatus() != BlackjackStatus.BLACKJACK;
    }

    public boolean isNotDead() {
        return getStatus() != BlackjackStatus.DEAD;
    }

    public Hands getHands() {
        return new Hands(hands.getCards());
    }

    public ParticipantName getName() {
        return name;
    }
}
