package domain.participant;

import static domain.result.BlackjackResultStatus.LOSE;
import static domain.result.BlackjackResultStatus.PUSH;
import static domain.result.BlackjackResultStatus.WIN;
import static domain.card.Hand.BLACKJACK_SCORE;

import domain.result.BlackjackResultStatus;
import domain.card.Card;
import domain.card.Hand;
import domain.participant.attributes.Name;

public abstract class Participant {

    private final Name name;
    private final Hand hand;

    public Participant(final Name name) {
        this.name = name;
        hand = new Hand();
    }

    public void receive(final Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return score() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return score() == BLACKJACK_SCORE;
    }

    public BlackjackResultStatus resultStatusAgainst(final Participant opponent) {
        if (isPush(opponent)) {
            return PUSH;
        }
        if (isLose(opponent)) {
            return LOSE;
        }
        return WIN;
    }

    private boolean isPush(final Participant opponent) {
        return isBothBust(opponent) || score() == opponent.score();
    }

    private boolean isLose(final Participant opponent) {
        if (opponent.isBust()) {
            return false;
        }
        return isBust() || (score() < opponent.score());
    }

    private boolean isBothBust(final Participant opponent) {
        return isBust() && opponent.isBust();
    }

    public Name name() {
        return name;
    }

    public Hand hand() {
        return hand;
    }

    public abstract int score();
}
