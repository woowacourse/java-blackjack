package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.Score;

import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private final Hand hand;
    protected Score score;

    public Participant() {
        this.hand = new Hand();
        this.score = new Score(0);
    }

    public void receiveCard(Card card) {
        hand.add(card);
        score = hand.calculateScore();
    }

    public boolean isBurst() {
        return score.isBurst();
    }

    public int getScore() {
        return score.getScore();
    }

    public String getInitialCards() {
        return hand.getFirst().getCardName();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hand.getCards());
    }

    public boolean isBlackjack() {
        return hand.getSize() == 2 && score.isBlackjack();
    }

    public abstract boolean canReceive();
}
