package blackjack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final Map<Player, Status> result;

    public Result() {
        result = new HashMap<>();
    }

    public Map<Player, Status> getResult(Dealer dealer, List<Player> players) {
        players.forEach(player -> result.put(player,compareWithDealer(dealer, player)));
        return result;
    }

    private Status compareWithDealer(Dealer dealer, Player player) {
        return Status.compare(dealer.getScore(),player.getScore());
    }
}
