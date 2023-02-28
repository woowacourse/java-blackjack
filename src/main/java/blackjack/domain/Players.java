package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(final String input) {
        this.players = makePlayers(input);
    }

    private List<Player> makePlayers(final String input) {
        List<String> names = Arrays.asList(input.split(","));
        validateNumberOfPlayers(names);
        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validateNumberOfPlayers(List<String> names) {
        if (names.size() < 1 || names.size() > 7) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상 7명 이하만 가능합나다.");
        }
    }


    public List<Player> getPlayers() {
        return this.players;
    }
}
