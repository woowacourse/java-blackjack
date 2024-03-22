package blackjack.domain.gamer;

import blackjack.domain.betting.BettingMoney;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;
    private static final String SIZE_ERROR_MESSAGE =
            String.format("플레이어의 수는 %d 이상 또는 %d 이하이어야 합니다.", MIN_SIZE, MAX_SIZE);
    static final String DUPLICATION_ERROR_MESSAGE = "플레이어 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(final List<Name> playerNames, final List<BettingMoney> bettingMonies) {
        List<Name> copyPlayerNames = new ArrayList<>(playerNames);
        List<BettingMoney> copyBettingMonies = new ArrayList<>(bettingMonies);

        validate(copyPlayerNames);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < copyPlayerNames.size(); i++) {
            players.add(new Player(copyPlayerNames.get(i), copyBettingMonies.get(i)));
        }

        this.players = players;
    }

    public Players(final List<Player> players) {
        this.players = players;
    }

    private static void validate(final List<Name> playerNames) {
        validateSize(playerNames);
        validateDuplication(playerNames);
    }

    private static void validateSize(final List<Name> playerNames) {
        if (playerNames.size() < MIN_SIZE || playerNames.size() > MAX_SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
    }

    private static void validateDuplication(final List<Name> playerNames) {
        int distinctSize = new HashSet<>(playerNames).size();
        if (playerNames.size() != distinctSize) {
            throw new IllegalArgumentException(DUPLICATION_ERROR_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }
}
