package blackjack.domain.game;

import blackjack.domain.betting.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.function.Function;

public enum GameResult {

    PLAYER_BLACKJACK((Money bet) -> bet.multiply(1.5)),
    PLAYER_WIN((Money bet) -> bet.multiply(1)),
    PUSH((Money bet) -> Money.ZERO),
    PLAYER_LOSE((Money bet) -> bet.multiply(-1)),
    ;

    private final Function<Money, Money> calculatePrize;

    GameResult(Function<Money, Money> calculatePrize) {
        this.calculatePrize = calculatePrize;
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
        return calculatePrize.apply(bet);
    }
}
