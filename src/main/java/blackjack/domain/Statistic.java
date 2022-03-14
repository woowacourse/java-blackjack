package blackjack.domain;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.LinkedHashMap;

public class Statistic {

    private LinkedHashMap<Player, GameResult> playerResult;

    private Statistic(Dealer dealer, Players players) {
        this.playerResult = new LinkedHashMap<>();
        calculate(playerResult, dealer, players);
    }

    public static Statistic of(Dealer dealer, Players players) {
        return new Statistic(dealer, players);
    }

    private void calculate(LinkedHashMap<Player, GameResult> playerResult, Dealer dealer,
        Players players) {
        if (dealer.isOverThanMaxPoint()) {
            calculateDealerBurst(playerResult, players);
            return;
        }
        calculateDealerNotBurst(playerResult, dealer, players);
    }

    private void calculateDealerBurst(LinkedHashMap<Player, GameResult> playerResult,
        Players players) {
        for (Player player : players.getPlayers()) {
            GameResult gameResult = getResultAtBurst(player);
            playerResult.put(player, gameResult);
        }
    }

    private GameResult getResultAtBurst(Player player) {
        if (!player.isOverThanMaxPoint()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    private void calculateDealerNotBurst(LinkedHashMap<Player, GameResult> playerResult,
        Dealer dealer, Players players) {
        int dealerPoint = dealer.getPoint();
        for (Player player : players.getPlayers()) {
            GameResult gameResult = getResultAtNotBurst(dealerPoint, player);
            playerResult.put(player, gameResult);
        }
    }

    private GameResult getResultAtNotBurst(int dealerPoint, Player player) {
        int playerPoint = player.getPoint();
        if (player.isOverThanMaxPoint() || dealerPoint > playerPoint) {
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

    public GameResult getGameResultByPlayer(Player player) {
        return playerResult.get(player);
    }
}
