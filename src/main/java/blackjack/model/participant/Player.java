package blackjack.model.participant;

import blackjack.model.BetAmount;
import blackjack.model.card.CardDeck;
import blackjack.model.result.Result;
import blackjack.model.state.FinalState;
import blackjack.model.state.PlayerDrawState;
import blackjack.model.state.State;

public class Player extends Participant {

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
        if (currentState instanceof PlayerDrawState) {
            this.currentState = ((PlayerDrawState) currentState).transitToStandState();
        }
    }

    public int getProfit(Dealer dealer) {
        if (!isFinished()) {
            throw new IllegalStateException("게임이 끝나지 않아 수익을 알 수 없습니다.");
        }

        if (getResult(dealer).equals(Result.WIN)) {
            return (int) (betAmount.getBetAmount() * ((FinalState) currentState).getProfitWeight());
        }
        if (getResult(dealer).equals(Result.TIE)) {
            return 0;
        }
        return -1 * (int) (betAmount.getBetAmount() * ((FinalState) currentState).getProfitWeight());
    }

    private Result getResult(Dealer dealer) {
        return Result.checkPlayerResult(this, dealer);
    }
}
