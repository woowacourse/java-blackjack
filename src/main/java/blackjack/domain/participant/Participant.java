package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected final CardBundle cardBundle;

    protected Participant(final CardBundle cardBundle) {
        this.cardBundle = cardBundle;
    }

    public void receiveCard(Card card) {
        cardBundle.add(card);
    }

    public abstract boolean canDraw();

    public Score getCurrentScore() {
        return cardBundle.getScore();
    }

    public boolean isBlackjack() {
        return cardBundle.isBlackjack();
    }

    public boolean isBust() {
        return cardBundle.isBust();
    }

    public abstract String getName();

    public abstract List<Card> getInitialOpenCards();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cardBundle.getCards());
    }
}
