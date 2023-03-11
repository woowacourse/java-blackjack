package blackjack.domain.user;

import blackjack.constants.ErrorCode;
import blackjack.domain.user.exception.DuplicatePlayerNameException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private final List<Player> players;
    private final Dealer dealer;

    private Participants(List<Player> players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public static Participants from(List<String> names) {
        validateDuplicate(names);
        List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Participants(players);
    }

    private static void validateDuplicate(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new DuplicatePlayerNameException(ErrorCode.DUPLICATE_NAME);
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<String> getPlayerNames() {
        return getPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
}
