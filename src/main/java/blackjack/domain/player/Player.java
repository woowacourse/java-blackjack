package blackjack.domain.player;

import static blackjack.domain.card.Cards.BLACKJACK_NUMBER;

import blackjack.domain.blackjackgame.Money;

public class Player extends Participant {

    private GameResult gameResult;

    public Player(String name) {
        super(name);
    }

    public GameResult getResult() {
        return gameResult;
    }

    public boolean canDraw() {
        return state.getScore() < BLACKJACK_NUMBER;
    }

    public void calculateGameResult(Dealer dealer) {
        if (this.isBust() || dealer.isBust()) {
            calculateBustGameResult(dealer);
            return;
        }
        calculateScoreGameResult(dealer);
    }

    private void calculateBustGameResult(Dealer dealer) {
        if (this.isBust()) {
            makePlayerGameResult(GameResult.LOSE, dealer);
            return;
        }
        makePlayerGameResult(GameResult.WIN, dealer);
    }

    private void calculateScoreGameResult(Dealer dealer) {
        if (this.getScore() > dealer.getScore()) {
            makePlayerGameResult(GameResult.WIN, dealer);
            return;
        }
        if (this.getScore() < dealer.getScore()) {
            makePlayerGameResult(GameResult.LOSE, dealer);
            return;
        }
        makePlayerGameResult(GameResult.DRAW, dealer);
    }

    private void makePlayerGameResult(GameResult gameResult, Dealer dealer) {
        this.gameResult = gameResult;
        dealer.addGameResult(GameResult.findOppositeGameResult(gameResult));
    }

    public Money profit(Dealer dealer) {
        if (this.isBlackjack() && dealer.isBlackjack()) {
            return money.profit(gameResult.earningRate());
        }
        if (this.isBust() || this.isBlackjack()) {
            return money.profit(state.earningRate());
        }
        return money.profit(gameResult.earningRate());
    }

}


