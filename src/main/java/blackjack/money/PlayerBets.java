package blackjack.money;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PlayerBets {

    private final Map<String, PlayerBet> playerBets;

    public PlayerBets() {
        this.playerBets = new LinkedHashMap<>();
    }

    public void addPlayerBet(String name, BetMoney betMoney) {
        playerBets.put(name, new PlayerBet(name, betMoney));
    }

    public Stream<PlayerBet> stream() {
        return playerBets.values()
                .stream();
    }
}
