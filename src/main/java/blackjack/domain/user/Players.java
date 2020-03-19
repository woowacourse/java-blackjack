package blackjack.domain.user;

import blackjack.domain.user.component.BettingAmount;
import blackjack.domain.user.component.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private static final String MAX_PLAYER_SIZE_ERR_MSG = "플레이어는 최대 5명입니다.";
    private static final String NULL_ERR_MSG = "%s이 없습니다.";
    public static final int MAX_PLAYER = 5;

    private final List<Player> players;

    public Players(List<String> names) {
        Objects.requireNonNull(names, String.format(NULL_ERR_MSG, "플레이어의 이름"));

        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(MAX_PLAYER_SIZE_ERR_MSG);
        }

        players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public Players(List<Name> names, List<BettingAmount> bettingAmounts) {
        validateNames(names);
        validateBettingAmounts(bettingAmounts);
        if (names.size() != bettingAmounts.size()) {
            throw new IllegalArgumentException("플레이어의 이름과 Betting 금액의 개수가 일치하지 않습니다.");
        }

        this.players = new ArrayList<>();
        int length = names.size();
        for (int i = 0; i < length; i++) {
            players.add(new Player(names.get(i), bettingAmounts.get(i)));
        }
    }

    private static void validateNames(List<Name> names) {
        Objects.requireNonNull(names, String.format(NULL_ERR_MSG, "플레이어의 이름"));
        if (names.isEmpty()) {
            throw  new IllegalArgumentException(String.format(NULL_ERR_MSG, "플레이어의 이름"));
        }
        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(MAX_PLAYER_SIZE_ERR_MSG);
        }
    }

    private static void validateBettingAmounts(List<BettingAmount> bettingAmounts) {
        Objects.requireNonNull(bettingAmounts, String.format(NULL_ERR_MSG, "Betting 금액"));
        if (bettingAmounts.isEmpty()) {
            throw  new IllegalArgumentException(String.format(NULL_ERR_MSG, "Betting 금액"));
        }
        if (bettingAmounts.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(MAX_PLAYER_SIZE_ERR_MSG);
        }
    }
    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(x -> x.getName().toString())
                .collect(Collectors.toList());
    }
}
