package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

public enum PlayerResult {
    BLACKJACKWIN(1.5),
    DRAW(0),
    WIN(1.0),
    LOSE(-1.0);

    private double resultState;

    PlayerResult(double resultState) {
        this.resultState = resultState;
    }

    public double getResultState() {
        return resultState;
    }

    public static PlayerResult match(Dealer dealer, Player player) {
        if(player.getPlayingCards().isBlackJack() && !dealer.getPlayingCards().isBlackJack()) {
            return PlayerResult.BLACKJACKWIN;
        } else if(player.isBust()) {
            return PlayerResult.LOSE;
        } else if((!player.isBust() && dealer.isBust()) || (player.calculateScore() > dealer.calculateScore())) {
            return PlayerResult.WIN;
        } else if(player.calculateScore() == dealer.calculateScore()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }
}
