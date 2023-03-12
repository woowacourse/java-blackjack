package blackjackgame.domain.user;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;

    public Players(Names names, List<Bet> bets) {
        validateSize(names, bets);
        players = new ArrayList<>();
        for (int playerCount = 0; playerCount < bets.size(); playerCount++) {
            Name name = names.getNames().get(playerCount);
            Bet bet = bets.get(playerCount);

            players.add(new Player(name, bet));
        }
    }

    private void validateSize(Names names, List<Bet> bets) {
        if (names.size() != bets.size()) {
            throw new IllegalArgumentException("입력된 플레이어와 베팅 금액의 수가 일치하지 않습니다.");
        }
    }

    public Map<Player, Profit> getPlayerProfits() {
        Map<Player, Profit> profitByPlayer = new LinkedHashMap<>();
        for (Player player : players) {
            profitByPlayer.put(player, player.getProfit());
        }
        return profitByPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
