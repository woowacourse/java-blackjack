package blackjack.domain.participants;


import blackjack.domain.Hands;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Map<Player, Boolean> calculateWinOrLose(int dealerScore) {
        Map<Player, Boolean> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.isWin(dealerScore)));
        return result;
    }

    public void distributeHands(List<Hands> allHands) {
        for (int index = 0; index < players.size(); index++) {
            Hands hands = allHands.get(index);
            Player player = players.get(index);
            player.receiveHands(hands);
        }
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
