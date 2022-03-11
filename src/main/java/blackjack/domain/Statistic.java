package blackjack.domain;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class Statistic {

    private static final int MAX_POINT = 21;

    private static Map<Player, GameResult> playerResult;

    private Statistic() {
    }

    public static Statistic of(Dealer dealer, Players players) {
        playerResult = new LinkedHashMap<>();
        calculate(dealer, players);
        return new Statistic();
    }

    private static void calculate(Dealer dealer, Players players) {
        if (dealer.getPoint() > MAX_POINT) {
            calculateDealerBurst(players);
            return;
        }
        calculateDealerNotBurst(dealer, players);
    }

    private static void calculateDealerBurst(Players players) {
        for (Player player : players.getPlayers()) {
            int point = player.getPoint();
            GameResult gameResult = getResultAtBurst(point);
            playerResult.put(player, gameResult);
        }
    }

    private static GameResult getResultAtBurst(int point) {
        if (point <= MAX_POINT) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    private static void calculateDealerNotBurst(Dealer dealer, Players players) {
        int dealerPoint = dealer.getPoint();
        for (Player player : players.getPlayers()) {
            GameResult gameResult = getResultAtNotBurst(dealerPoint, player);
            playerResult.put(player, gameResult);
        }
    }

    private static GameResult getResultAtNotBurst(int dealerPoint, Player player) {
        int playerPoint = player.getPoint();
        if (playerPoint > MAX_POINT || dealerPoint > playerPoint) {
            return GameResult.LOSE;
        }
        if (dealerPoint == playerPoint) {
            return GameResult.DRAW;
        }
        return GameResult.WIN;
    }

    public int getCountByGameResult(GameResult inputGameResult) {
        return (int) playerResult.values().stream()
            .filter(gameResult -> gameResult.equals(inputGameResult))
            .count();
    }

    public String getGameResultByPlayer(Player player) {
        return playerResult.get(player).getResult();
    }
}
