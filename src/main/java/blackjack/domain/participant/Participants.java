package blackjack.domain.participant;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final String EMPTY_ERROR_MESSAGE = "참가자들이 존재하지 않습니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 이름이 존재합니다.";

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final List<String> playerNames) {
        validate(playerNames);

        this.dealer = dealer;
        this.players = makePlayers(playerNames);
    }

    private void validate(final List<String> playerNames) {
        validateEmptyNames(playerNames);
        validateDuplicateName(playerNames);
    }

    private void validateEmptyNames(final List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateName(final List<String> names) {
        if (isDuplicateName(names)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicateName(final List<String> names) {
        final HashSet<String> uniqueNames = new HashSet<>(names);
        return uniqueNames.size() != names.size();
    }

    private List<Player> makePlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(name -> new Player(new Name(name)))
                .collect(Collectors.toUnmodifiableList());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
}
