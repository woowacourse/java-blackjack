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
        return cards.getScore() < BLACKJACK_NUMBER;
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
        }
        if (!this.isBust() && dealer.isBust()) {
            dealer.addGameResult(GameResult.LOSE);
            gameResult = GameResult.WIN;
        }
    }

    private void calculateScoreGameResult(Dealer dealer) {
        if (this.getScore() > dealer.getScore()) {
            dealer.addGameResult(GameResult.LOSE);
            gameResult = GameResult.WIN;
        }
        if (this.getScore() < dealer.getScore()) {
            dealer.addGameResult(GameResult.WIN);
            gameResult = GameResult.LOSE;
        }
    }

    public Money profit() {
        return money.profit(gameResult, isBlackjack());
    }

}
