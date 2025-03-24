package blackjack.blackjack.participant;

import blackjack.blackjack.card.Card;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import blackjack.blackjack.state.running.PlayerRunningState;
import blackjack.util.ExceptionMessage;
import java.math.BigDecimal;

public final class Player implements Participant {

    private static final BigDecimal BLACKJACK_PROFIT_RATE = new BigDecimal("1.5");

    private final String nickname;
    private final BigDecimal bettingAmount;
    private State state;

    public Player(final Hand givenHand, final String nickname, final BigDecimal bettingAmount) {
        validateBettingAmount(bettingAmount);
        this.nickname = nickname;
        this.bettingAmount = bettingAmount;
        this.state = PlayerRunningState.initialState(givenHand);
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

    public BigDecimal calculateProfit(final Dealer dealer) {
        if (state.getStateType() == StateType.BUST) {
            return bettingAmount.negate();
        }
        if (dealer.getStateType() == StateType.BUST) {
            return bettingAmount;
        }
        return compareByScore(bettingAmount, dealer);
    }

    public void stay() {
        changeState(state.stay());
    }

    private void validateBettingAmount(final BigDecimal bettingAmount) {
        if (bettingAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.makeMessage("베팅 금액을 양수로 입력해주세요."));
        }
    }

    private BigDecimal compareByScore(final BigDecimal bettingAmount, final Dealer dealer) {
        int playerScore = calculateScore();
        int dealerScore = dealer.calculateScore();
        if (playerScore == dealerScore) {
            return BigDecimal.ZERO;
        }
        if (playerScore < dealerScore) {
            return bettingAmount.negate();
        }
        if (state.getStateType() == StateType.BLACKJACK) {
            return bettingAmount.multiply(BLACKJACK_PROFIT_RATE);
        }
        return bettingAmount;
    }

    private int calculateScore() {
        return state.calculateScore();
    }

    public State getState() {
        return state;
    }
}
