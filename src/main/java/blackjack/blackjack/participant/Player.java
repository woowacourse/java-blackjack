package blackjack.blackjack.participant;

import blackjack.blackjack.card.Card;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.running.PlayerRunning;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import blackjack.util.ExceptionMessage;
import java.math.BigDecimal;
import java.util.ArrayList;

public final class Player extends Participant {

    private static final int SPREAD_CARD_SIZE = 2;

    private final String nickname;
    private final BigDecimal bettingAmount;
    private State state;

    public Player(final Hand givenHand, final String nickname, final BigDecimal bettingAmount) {
        validateBettingAmount(bettingAmount);
        this.nickname = nickname;
        this.bettingAmount = bettingAmount;
        this.state = PlayerRunning.initialState(givenHand);
    }

    public Player(final String nickname, final BigDecimal bettingAmount) {
        this(new Hand(new ArrayList<>()), nickname, bettingAmount);
    }

    @Override
    public Hand showInitialCards() {
        return state.cards();
    }

    @Override
    public boolean canHit() {
        return state.isNotFinished();
    }

    @Override
    public int calculateScore() {
        return state.calculateScore();
    }

    @Override
    public void changeState(final State inputState) {
        this.state = inputState;
    }

    @Override
    public Hand showAllCards() {
        return state.cards();
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public void receiveCards(final Hand hand) {
        changeState(state.receiveCards(hand));
    }

    public void receiveCard(final Card card) {
        changeState(state.receiveCards(new Hand(card)));
    }

    public BigDecimal calculateProfit(final State dealerState) {
        return state.calculateProfit(bettingAmount, dealerState);
    }

    public void stayIfRunning() {
        changeState(state.stay());
    }

    private void validateBettingAmount(final BigDecimal bettingAmount) {
        if (bettingAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.makeMessage("베팅 금액을 양수로 입력해주세요."));
        }
    }

    public BigDecimal getBettingAmount() {
        return bettingAmount;
    }

    public State getState() {
        return state;
    }
}
