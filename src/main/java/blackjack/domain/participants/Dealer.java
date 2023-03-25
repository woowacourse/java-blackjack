package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;
import blackjack.domain.participants.status.Status;
import blackjack.domain.participants.status.running.Hit;

import java.util.List;

public class Dealer {

    private static final int FIRST_CARD_INDEX = 0;
    private static final int DEALER_DRAW_POINT = 16;
    private Status status;

    public Dealer() {
        status = new Hit();
    }

    public void drawInitialCards(final Card card1, final Card card2) {
        status = status.initCards(card1, card2);
    }

    public void drawCard(final Card card) {
        status = status.addCard(card);
    }

    public Card getInitialCard() {
        return status.getCards()
                .get(FIRST_CARD_INDEX);
    }

    public boolean isDrawable() {
        final Score currentScore = score();
        return currentScore.getValue() <= DEALER_DRAW_POINT;
    }

    public Score score() {
        return status.score();
    }

    public List<Card> getCards() {
        return status.getCards();
    }

    //임시
    public Status getStatus() {
        return status;
    }

}
