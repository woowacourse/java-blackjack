package domain.participant;

import domain.bet.BettingMoney;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> playerList;

    public Players(Map<String, Integer> nameToBet) {
        playerList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : nameToBet.entrySet()) {
            playerList.add(new Player(entry.getKey(), new BettingMoney(entry.getValue())));
        }
    }

    public List<Player> getGamePlayers() {
        return playerList;
    }

    public int getSize() {
        return playerList.size();
    }
}
