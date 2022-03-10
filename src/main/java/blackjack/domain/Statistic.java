package blackjack.domain;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;

public class Statistic {
    private final int dealerPoint;
    private final Players players;

    private Statistic(Dealer dealer, Players players) {
        this.dealerPoint = dealer.getPoint();
        this.players = players;
    }

    public static Statistic of(Dealer dealer, Players players) {
        return new Statistic(dealer,players);
    }

    public void calculate() {
        if (dealerPoint > 21) {
            calculateIfDealerBurst();
            return;
        }
        calculateIfDealerNotBurst();
    }

    private void calculateIfDealerBurst() {
        int playerWinCount = 0;
        for (Player player : players.getPlayers()) {
            int point = player.getPoint();
            if (point <= 21) {
                // 플레이어 승리
                player.setResult(GameResult.WIN);
                playerWinCount++;
                continue;
            }
            // 플레이어 패배
            player.setResult(GameResult.LOSE);
        }
        if (playerWinCount == 0) {
            // 플레이어 패배
            for (Player player : players.getPlayers()) {
                player.setResult(GameResult.LOSE);
            }
        }
    }

    private void calculateIfDealerNotBurst() {
        for (Player player : players.getPlayers()) {
            int point = player.getPoint();
            if (point > 21 || dealerPoint > point) {
                // 플레이어 패배
                player.setResult(GameResult.LOSE);
                continue;
            }
            if (dealerPoint == point) {
                // 무승부
                player.setResult(GameResult.DRAW);
                continue;
            }
            // 플레이어 승리
            player.setResult(GameResult.WIN);
        }
    }
}
