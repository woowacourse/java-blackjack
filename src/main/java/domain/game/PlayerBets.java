package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerBets {
    private final Map<Player, BetMoney> playerBets;

    public PlayerBets(Map<Player, BetMoney> playerBets) {
        this.playerBets = playerBets;
    }

    public EarningResult evaluateEarning(List<Player> players, Dealer dealer) {
        Map<Player, Double> earningResult = players.stream()
                .collect(Collectors.toMap(player -> player
                        , player -> Winning.determine(player.getHands(), dealer.getHands())
                                .getEarningRate() * getBetAmountByPlayer(player)
                        , (player1, player2) -> player1, LinkedHashMap::new));

        return new EarningResult(earningResult);
    }

    public int getBetAmountByPlayer(Player player) {
        return playerBets.get(player).getAmount();
    }
}
