package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.domain.state.State;

import java.util.List;


public class Dealer extends AbstractParticipant {
    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int DRAW_BOUND_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(State state) {
        super(DEALER_NAME, state);
        changeState();
    }

    @Override
    boolean isReceivable() {
        int totalScore = sumTotalScore(new BlackJackScoreRule());
        return totalScore <= DRAW_BOUND_SCORE && !isEnd();
    }

    @Override
    boolean handOutCard(Card card) {
        if (!isReceivable()) {
            return false;
        }

        receiveCard(card);
        changeState();
        checkStay();

        return false;
    }

    @Override
    boolean isDealer() {
        return true;
    }

    @Override
    List<Card> showInitCards() {
        return getState().getCards().splitCardsFromTo(FROM, TO);
    }

    private void checkStay() {
        if (!isEnd() && sumTotalScore(new BlackJackScoreRule()) >= DRAW_BOUND_SCORE) {
            stay();
        }
    }
}
