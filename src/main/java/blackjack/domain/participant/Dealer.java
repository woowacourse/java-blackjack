package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.rule.BlackJackScoreRule;
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
    public boolean handOutCard(Card card) {
        if (isReceiveCard()) {
            receiveCard(card);
            return true;
        }

        if (!state.isEndState()) {
            stay();
        }
        return false;
    }

    private void receiveCard(Card card) {
        state.draw(card);
        this.state = state.changeState();
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
        return totalScore <= DRAW_BOUND_SCORE && !state.isEndState();
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
