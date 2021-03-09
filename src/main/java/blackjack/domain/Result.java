package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    private final Map<Player, Status> result;

    public Result(Dealer dealer, Players players) {
        this.result = makeResult(dealer, players);
    }

    private Map<Player, Status> makeResult(Dealer dealer, Players players) {
        Map<Player, Status> record = new LinkedHashMap<>();
        players.getPlayers()
            .forEach(player -> record.put(player, compareWithDealer(dealer, player)));
        return record;
    }

    private Status compareWithDealer(Dealer dealer, Player player) {
        return Status.compare(dealer.getScore(), player.getScore());
    }

    public Map<Player, Status> getResult() {
        return new LinkedHashMap<>(result);
    }
}
