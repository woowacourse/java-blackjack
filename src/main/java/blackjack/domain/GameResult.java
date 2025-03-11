package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {
    BLACKJACK_WIN("블랙잭", 1.5),
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    private final String status;
    private final double payoutRate;

    GameResult(String status, double payoutRate) {
        this.status = status;
        this.payoutRate = payoutRate;
    }

    public static GameResult checkPlayerWin(final Player player, final Dealer dealer) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        if(player.isBlackjack() && dealer.isBlackjack()) {
            return GameResult.DRAW;
        }

        if(player.isBlackjack()) {
            return GameResult.BLACKJACK_WIN;
        }
        if(dealer.isBlackjack()) {
            return GameResult.LOSE;
        }

        if (player.getScore().isHigherThan(dealer.getScore())) {
            return GameResult.WIN;
        }
        if (dealer.getScore().isHigherThan(player.getScore())) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public static GameResult checkDealerWin(final Player player, final Dealer dealer) {
        GameResult gameResult = checkPlayerWin(player, dealer);

        if (gameResult == GameResult.WIN || gameResult == GameResult.BLACKJACK_WIN) {
            return GameResult.LOSE;
        }
        if (gameResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public int calculateOutcome(BettingMoney bettingMoney) {
        return (int)(bettingMoney.intValue() * payoutRate);
    }

    public String getStatus() {
        return status;
    }
}
