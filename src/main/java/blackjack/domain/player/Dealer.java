package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int ADD_CARD_CONDITION = 16;
    private static final int FIRST = 0;
    private static final String DEALER_NAME = "딜러";

    private int winCount = 0;
    private int loseCount = 0;

    public Dealer(final List<Card> cards) {
        super(cards, DEALER_NAME);
    }

    public void compete(final Participant participant) {
        if (isDealerWin(calculateFinalScore(), participant.calculateFinalScore())) {
            winCount += 1;
            return;
        }
        participant.win();
        this.loseCount += 1;
    }

    private boolean isDealerWin(final int dealerScore, final int participantScore) {
        return participantScore > MAX_SCORE || (dealerScore <= MAX_SCORE && dealerScore >= participantScore);
    }

    public boolean acceptableCard() {
        return getScoreByAceEleven() <= ADD_CARD_CONDITION;
    }

    public Card getCardFirstOne() {
        return getCards().get(FIRST);
    }

    public int getWinCount() {
        return this.winCount;
    }

    public int getLoseCount() {
        return this.loseCount;
    }
}
