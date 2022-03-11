package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;
import java.util.Collections;
import java.util.Set;

public abstract class Participant {

    protected final String name;
    protected CardBundle cardBundle;

    protected Participant(final String name, final CardBundle cardBundle) {
        this.name = name;
        this.cardBundle = cardBundle;
    }

    public void receiveCard(Card card) {
        cardBundle.add(card);
    }
    public abstract boolean canReceive();

    public Score getCurrentScore() {
        return cardBundle.getScore();
    }

    public boolean isBust() {
        return cardBundle.getScore().toInt() > Score.BLACKJACK;
    }

    public String getName() {
        return name;
    }

    public Set<Card> getCards() {
        return Collections.unmodifiableSet(cardBundle.getCards());
    }
}
