package blackjack.domain.game;

import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {

    PLAYER_BLACKJACK(1.5),
    PLAYER_WIN(1),
    PUSH(0),
    PLAYER_LOSE(-1);

    private final double betPrizeRatio;

    GameResult(double betPrizeRatio) {
        this.betPrizeRatio = betPrizeRatio;
    }

    public static GameResult of(Dealer dealer, Player player) {
        if (player.isBusted()) {
            return PLAYER_LOSE;
        }
        if (player.isBlackjack()) {
            return blackJackPlayer(dealer);
        }
        return normalPlayer(dealer, player);
    }

    private static GameResult blackJackPlayer(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return PUSH;
        }
        return PLAYER_BLACKJACK;
    }

    private static GameResult normalPlayer(Dealer dealer, Player player) {
        if (dealer.isBlackjack()) {
            return PLAYER_LOSE;
        }
        if (dealer.isBusted()) {
            return PLAYER_WIN;
        }
        return compareScoreResult(dealer, player);
    }

    private static GameResult compareScoreResult(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();
        if (dealerScore > playerScore) {
            return PLAYER_LOSE;
        }
        if (dealerScore == playerScore) {
            return PUSH;
        }
        return PLAYER_WIN;
    }

    public Money calculatePrize(Money bet) {
        return bet.multiply(betPrizeRatio);
    }
}
