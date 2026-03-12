package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class Bettings {

    // TODO: key는 String(PlayerName)이 좋을까? Player가 좋을까?
    private final Map<Player, BettingAmount> bettings;

    public Bettings() {
        this.bettings = new HashMap<>();
    }

    public void put(Player playerName, BettingAmount bettingAmount) {
        bettings.put(playerName, bettingAmount);
    }

    public BettingAmount findByPlayer(Player player) {
        return bettings.get(player);
    }

}
