package blackjack.domain.participants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.result.MoneyResult;

public class Players {
    private static final String SPLIT_DELIMITER = ",";

    List<Player> players;

    public Players(String names) {
        this.players = splitNames(names);
    }

    public void initMoney(List<String> moneys, MoneyResult moneyResult) {
        for (int i = 0; i < players.size(); i++) {
            moneyResult.initPlayerMoney(players.get(i), moneys.get(i));
        }
    }

    private List<Player> splitNames(String names) {
        return Arrays.stream(names
            .split(SPLIT_DELIMITER))
            .map(Player::new)
            .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
