package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private final List<Card> cards;
    protected final Score score;

    public Participant() {
        this.cards = new ArrayList<>();
        this.score = new Score();
    }

    public void receiveCard(Card card) {
        cards.add(card);
        score.calculateAll(cards);
    }

    public boolean isBurst() {
        return score.isBurst();
    }

    public int getScore() {
        return score.getScore();
    }

    public String getInitialCards() {
        return cards.getFirst().getCardName();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && score.isBlackjack();
    }

    public abstract boolean canReceive();
}
