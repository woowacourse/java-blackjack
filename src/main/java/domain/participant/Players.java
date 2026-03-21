package domain.participant;

import domain.game.ProfitResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> playerList;

    public Players(Map<String, Integer> nameToBet) {
        playerList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : nameToBet.entrySet()) {
            playerList.add(new Player(entry.getKey(), entry.getValue()));
        }
    }

    public ProfitResult calculateProfits(Dealer dealer) {
        Map<Player, Integer> playerProfits = new LinkedHashMap<>();
        for (Player player : playerList) {
            playerProfits.put(player, player.calculateProfit(dealer));
        }
        return new ProfitResult(playerProfits);
    }

    public List<Player> getGamePlayers() {
        return List.copyOf(playerList);
    }

    public int getSize() {
        return playerList.size();
    }
}
