package blackjack.domain;

import static blackjack.domain.HandState.BLACKJACK;
import static blackjack.domain.HandState.BUST;

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
        HandState playerState = player.getHandState();
        HandState dealerState = dealer.getHandState();
        if (playerState == BUST) {
            return GameResult.LOSE;
        }
        if (dealerState == BUST) {
            return GameResult.WIN;
        }

        if(playerState == BLACKJACK && dealerState == BLACKJACK) {
            return GameResult.DRAW;
        }

        if(playerState == BLACKJACK) {
            return GameResult.BLACKJACK_WIN;
        }
        if(dealerState == BLACKJACK) {
            return GameResult.LOSE;
        }

        Score playerScore = player.getScore();
        Score dealerScore = dealer.getScore();
        if (playerScore.isHigherThan(dealerScore)) {
            return GameResult.WIN;
        }
        if (dealerScore.isHigherThan(playerScore)) {
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

    public int calculatePayout(BettingMoney bettingMoney) {
        return (int)(bettingMoney.intValue() * payoutRate);
    }

    public String getStatus() {
        return status;
    }
}
