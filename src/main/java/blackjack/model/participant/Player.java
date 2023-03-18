package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.result.Result;
import blackjack.model.state.FinalState;
import blackjack.model.state.PlayerDrawState;
import blackjack.model.state.State;
import blackjack.model.state.StateValue;

public class Player extends Participant {

    private static final int LOSE_MONEY = -1;
    private static final int GET_NOTHING = 0;
    private static final int GET_MONEY = 1;

    private final BetAmount betAmount;

    public Player(Name name, BetAmount betAmount, State currentState) {
        super(name, currentState);
        this.betAmount = betAmount;
    }

    @Override
    public void play(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
    }

    public void changeToStand() {
        if (currentState.isStateOf(StateValue.DRAW)) {
            this.currentState = ((PlayerDrawState) currentState).transitToStandState();
        }
    }

    public int getProfit(Result result) {
        if (!isFinished()) {
            throw new IllegalStateException("게임이 끝나지 않아 수익을 알 수 없습니다.");
        }

        if (result.equals(Result.WIN)) {
            return GET_MONEY * reflectWeightOnBetAmount();
        }
        if (result.equals(Result.TIE)) {
            return GET_NOTHING;
        }
        return LOSE_MONEY * reflectWeightOnBetAmount();
    }

    private int reflectWeightOnBetAmount() {
        return (int) (betAmount.getBetAmount() * ((FinalState) currentState).getProfitWeight());
    }
}
