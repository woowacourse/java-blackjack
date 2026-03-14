package blackjack.domain.bet;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Bets {

    private final Map<Player, Bet> bets;

    public Bets() {
        bets = new HashMap<>();
    }

    public void playerBet(Player player, int betAmount) {
        bets.put(player, new Bet(betAmount));
    }

    public Set<Entry<Player, Bet>> getPlayerBets() {
        return bets.entrySet();
    }

    public Set<Entry<Player, Long>> getBets() {
        bets.entrySet()
                .forEach(playerBetEntry -> {
                    Player player = playerBetEntry.getKey();
                    Bet bet = playerBetEntry.getValue();
                });
        return null;
    }
}
