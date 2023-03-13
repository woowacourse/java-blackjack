package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;

import java.util.List;

public class Dealer {

    private static final int FIRST_CARD_INDEX = 1;
    private static final int DEALER_DRAW_POINT = 16;
    private final CardPocket cardPocket;

    public Dealer() {
        cardPocket = new CardPocket();
    }

    public void drawCard(final Card card) {
        cardPocket.addCard(card);
    }

    public Card getInitialCard() {
        return cardPocket.getPossessedCards()
                .get(FIRST_CARD_INDEX);
    }

    public boolean isDrawable() {
        final Score currentScore = currentScore();
        return currentScore.getValue() <= DEALER_DRAW_POINT;
    }

    public Score currentScore() {
        return cardPocket.getScore();
    }

    public boolean isBlackjack() {
        return cardPocket.isBlackjack();
    }

    public boolean isBusted() {
        return cardPocket.isBusted();
    }

    public int getDrawPoint() {
        return DEALER_DRAW_POINT;
    }

    public List<Card> getCards() {
        return cardPocket.getPossessedCards();
    }

}
