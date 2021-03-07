package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final Map<Player, Status> result;

    public Result(Dealer dealer, List<Player> players) {
        this.result = makeResult(dealer, players);
    }

    private Map<Player, Status> makeResult(Dealer dealer, List<Player> players) {
        players.forEach(player -> result.put(player, compareWithDealer(dealer, player)));
        return new LinkedHashMap<>(result);
    }

    private Status compareWithDealer(Dealer dealer, Player player) {
        return Status.compare(dealer.getScore(), player.getScore());
    }

    public Map<Player, Status> getResult() {
        return new LinkedHashMap<>(result);
    }
}
