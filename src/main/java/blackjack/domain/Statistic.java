package blackjack.domain;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Statistic {

    private static Map<Player, GameResult> playerResult;
//    private final int dealerPoint;
//    private final Players players;

    private Statistic(Map<Player, GameResult>playerResult) {
//        this.dealerPoint = dealer.getPoint();
//        this.players = players;
    }

    public static Statistic of(Dealer dealer, Players players) {
        playerResult = new LinkedHashMap<>();
        calculate(dealer, players);
        return new Statistic(playerResult);
    }

    private static void calculate(Dealer dealer, Players players) {
        if (dealer.getPoint() > 21) {
            calculateIfDealerBurst(players);
            return;
        }
        calculateIfDealerNotBurst(dealer, players);
    }

    private static void calculateIfDealerBurst(Players players) {
        int playerWinCount = 0;
        for (Player player : players.getPlayers()) {
            int point = player.getPoint();
            if (point <= 21) {
                // 플레이어 승리
//                player.setResult(GameResult.WIN);
                playerResult.put(player, GameResult.WIN);
                playerWinCount++;
                continue;
            }
            // 플레이어 패배
//            player.setResult(GameResult.LOSE);
            playerResult.put(player, GameResult.LOSE);
        }
        if (playerWinCount == 0) {
            // 플레이어 패배
            for (Player player : players.getPlayers()) {
//                player.setResult(GameResult.LOSE);
                playerResult.put(player, GameResult.LOSE);
            }
        }
    }

    private static void calculateIfDealerNotBurst(Dealer dealer, Players players) {
        int dealerPoint = dealer.getPoint();
        for (Player player : players.getPlayers()) {
            int point = player.getPoint();
            if (point > 21 || dealerPoint > point) {
                // 플레이어 패배
//                player.setResult(GameResult.LOSE);
                playerResult.put(player, GameResult.LOSE);
                continue;
            }
            if (dealerPoint == point) {
                // 무승부
//                player.setResult(GameResult.DRAW);
                playerResult.put(player, GameResult.DRAW);
                continue;
            }
            // 플레이어 승리
//            player.setResult(GameResult.WIN);
            playerResult.put(player, GameResult.WIN);
        }
    }

    public int getCountByGameResult(GameResult inputGameResult){
        return (int)playerResult.values().stream()
            .filter(gameResult -> gameResult.equals(inputGameResult))
            .count();
    }
    public String getGameResultByPlayer(Player player){
        return playerResult.get(player).getResult();
    }
}
