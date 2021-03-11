package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.domain.rule.ScoreRule;
import blackjack.domain.state.State;

import java.util.*;

public class Dealer implements Participant {
    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int DRAW_BOUND_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    private final String name;
    private State state;

    public Dealer(State state) {
        this.name = DEALER_NAME;
        this.state = state.changeState();
    }

    @Override
    public boolean receiveCard(Card card) {
        if (state.isEndState()) {
            return false;
        }

        if (isReceiveCard()) {
            state.draw(card);
            this.state = state.changeState();
            return true;
        }

        stay();
        return false;
    }

    @Override
    public List<Card> showInitCards() {
        return state.getCards().splitCardsFromTo(FROM, TO);
    }

    @Override
    public List<Card> showCards() {
        return state.getCards().toCardList();
    }

    @Override
    public boolean isReceiveCard() {
        int totalScore = state.getCards().getTotalScore(new BlackJackScoreRule());
        return totalScore <= DRAW_BOUND_SCORE;
    }

    @Override
    public int sumTotalScore(ScoreRule scoreRule) {
        return state.getCards().getTotalScore(scoreRule);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public State getStatus() {
        return state;
    }

    @Override
    public void betting(int money) {
    }

    @Override
    public void stay() {
        state = state.stay();
    }

    public int payWinPrize(int winPrize) {
        return winPrize * -1;
    }
}
