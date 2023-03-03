package domain.game;

import domain.player.Dealer;
import domain.player.DealerCompeteResult;
import domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameStatistic {

    private final Dealer dealer;
    private final List<Player> players;
    private final Map<Player, DealerCompeteResult> dealerResultPerPlayer;

    public GameStatistic(final Dealer dealer, final List<Player> players, final Map<Player, DealerCompeteResult> dealerResultPerPlayer) {
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
        this.dealerResultPerPlayer = dealerResultPerPlayer;
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Player> players() {
        return new ArrayList<>(players);
    }

    public Map<Player, DealerCompeteResult> dealerResultPerPlayer() {
        return dealerResultPerPlayer;
    }
}
