package domain.participant;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Player> findAllPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<Player, Integer> judgeAllPlayersIncomes(Dealer dealer) {
        Map<Player, Integer> incomes = new HashMap<>();
        for (Player player : players) {
            int income = player.calculateIncome(dealer);
            incomes.put(player, income);
        }
        return incomes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(players);
    }
}
