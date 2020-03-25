package blackjack.domain.participants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.result.MoneyResult;

public class Players {
    private static final String SPLIT_DELIMITER = ",";
    private static final int MIN = 2;
    private static final int MAX = 8;

    private final List<Player> players;

    public Players(String names) {
        this.players = validate(splitNames(names));
    }

    private List<Player> validate(List<Player> players) {
        if (players.size() < MIN || players.size() > MAX) {
            throw new IllegalArgumentException("참여하려는 인원 수가 너무 적거나 많습니다!");
        }
        return players;
    }

    public MoneyResult initMoney(List<String> moneys) {
        MoneyResult moneyResult = new MoneyResult();

        for (int i = 0; i < players.size(); i++) {
            moneyResult.initPlayerMoney(players.get(i), moneys.get(i));
        }

        return moneyResult;
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
