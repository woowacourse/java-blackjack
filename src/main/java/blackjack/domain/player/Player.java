package blackjack.domain.player;

import static blackjack.domain.card.Cards.BLACKJACK_NUMBER;

import blackjack.domain.card.GameResult;

public class Player extends Participant {

    private GameResult gameResult;

    public Player(String name) {
        super(name);
        gameResult = GameResult.NONE;
    }

    public GameResult getResult() {
        return gameResult;
    }

    public boolean canDraw() {
        return cards.getScore() < BLACKJACK_NUMBER;
    }

    public void calculateGameResult(Dealer dealer) {
        gameResult = calculateBustGameResult(dealer);
        if (gameResult != GameResult.NONE) {
            return;
        }
        gameResult = calculateScoreGameResult(dealer);
    }

    private GameResult calculateBustGameResult(Dealer dealer) {
        if (this.isBust()) {
            dealer.addGameResult(GameResult.WIN);
            return GameResult.LOSE;
        }
        if (!this.isBust() && dealer.isBust()) {
            dealer.addGameResult(GameResult.LOSE);
            return GameResult.WIN;
        }
        return GameResult.NONE;
    }

    private GameResult calculateScoreGameResult(Dealer dealer) {
        if (this.getScore() > dealer.getScore()) {
            dealer.addGameResult(GameResult.LOSE);
            return GameResult.WIN;
        }
        if (this.getScore() < dealer.getScore()) {
            dealer.addGameResult(GameResult.WIN);
            return GameResult.LOSE;
        }
        dealer.addGameResult(GameResult.DRAW);
        return GameResult.DRAW;
    }

}
