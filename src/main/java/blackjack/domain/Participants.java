package blackjack.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    private Participants(List<Player> players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public static Participants of(List<String> playerNames) {
        validatePlayerNames(playerNames);
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Participants(players);
    }

    private static void validatePlayerNames(List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }
}
