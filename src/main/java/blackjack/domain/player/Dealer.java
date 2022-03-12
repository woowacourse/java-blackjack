package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int ADD_CARD_CONDITION = 16;
    private static final int FIRST = 0;
    private static final String DEALER_NAME = "딜러";

    public Dealer(final List<Card> cards) {
        super(cards, DEALER_NAME);
    }

    @Override
    public boolean acceptableCard() {
        return getScoreByAceEleven() <= ADD_CARD_CONDITION;
    }

    public void compete(final Participant participant) {
        if (isDealerWin(calculateFinalScore(), participant.calculateFinalScore())) {
            this.win();
            return;
        }
        participant.win();
        this.lose();
    }

    private boolean isDealerWin(final int dealerScore, final int participantScore) {
        return participantScore > MAX_SCORE || (dealerScore <= MAX_SCORE && dealerScore >= participantScore);
    }

    public Card getCardFirstOne() {
        return getCards().get(FIRST);
    }
}
