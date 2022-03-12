package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int ADD_CARD_CONDITION = 16;
    private static final int FIRST = 0;
    private static final String DEALER_NAME = "딜러";

    private final ResultCount win;
    private final ResultCount lose;

    public Dealer(final List<Card> cards) {
        super(cards, DEALER_NAME);
        this.win = new ResultCount(Result.WIN);
        this.lose = new ResultCount(Result.LOSE);
    }

    public void compete(final Participant participant) {
        if (isDealerWin(calculateFinalScore(), participant.calculateFinalScore())) {
            win.increaseCount();
            return;
        }
        participant.win();
        lose.increaseCount();
    }

    private boolean isDealerWin(final int dealerScore, final int participantScore) {
        return participantScore > MAX_SCORE || (dealerScore <= MAX_SCORE && dealerScore >= participantScore);
    }

    public boolean acceptableCard() {
        return cards.calculateScoreByAceEleven() <= ADD_CARD_CONDITION;
    }

    public Card getCardFirstOne() {
        return getCards().get(FIRST);
    }

    public ResultCount getWinResultCount() {
        return this.win;
    }

    public ResultCount getLoseResultCount() {
        return this.lose;
    }
}
