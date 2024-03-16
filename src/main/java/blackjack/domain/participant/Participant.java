package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.result.BlackjackStatus;
import blackjack.domain.result.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void addCard(final List<Card> cards) {
        cards.forEach(hands::add);
    }

    protected BlackjackStatus getStatus() {
        return BlackjackStatus.of(calculate(), hands.count());
    }

    public boolean isAlive() {
        return getStatus() == BlackjackStatus.ALIVE;
    }

    public boolean isBlackjack() {
        return getStatus() == BlackjackStatus.BLACKJACK;
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public Hands getHands() {
        return new Hands(hands.getCards());
    }

    public ParticipantName getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
