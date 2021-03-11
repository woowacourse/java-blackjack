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
        if (this.isBust() || !this.isBust() && dealer.isBust()) {
            calculateBustGameResult(dealer);
            return;
        }
        if (this.getScore() == dealer.getScore()) {
            dealer.addGameResult(GameResult.DRAW);
            gameResult = GameResult.DRAW;
            return;
        }
        calculateScoreGameResult(dealer);
    }

    private void calculateBustGameResult(Dealer dealer) {
        if (this.isBust()) {
            dealer.addGameResult(GameResult.WIN);
            gameResult = GameResult.LOSE;
            return;
        }
        if (dealer.isBust()) {
            dealer.addGameResult(GameResult.LOSE);
            gameResult = GameResult.WIN;
            return;
        }
    }

    private void calculateScoreGameResult(Dealer dealer) {
        if (this.getScore() > dealer.getScore()) {
            dealer.addGameResult(GameResult.LOSE);
            gameResult = GameResult.WIN;
            return;
        }
        if (this.getScore() < dealer.getScore()) {
            dealer.addGameResult(GameResult.WIN);
            gameResult = GameResult.LOSE;
            return;
        }
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


