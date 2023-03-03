package blackjack.domain.participant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final String EMPTY_ERROR_MESSAGE = "참가자들이 존재하지 않습니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 이름이 존재합니다.";
    private static final String COMMA = ",";

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final String playerNames) {
        validate(playerNames);

        this.dealer = dealer;
        this.players = makePlayers(playerNames);
    }

    private void validate(final String playerNames) {
        List<String> names = Arrays.stream(playerNames.split(COMMA))
                .map(String::strip)
                .collect(Collectors.toList());

        validateEmptyNames(names);
        validateDuplicateName(names);
    }

    private void validateEmptyNames(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateName(List<String> names) {
        if (isDuplicateName(names)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicateName(List<String> names) {
        HashSet<String> uniqueNames = new HashSet<>(names);
        return uniqueNames.size() != names.size();
    }

    private List<Player> makePlayers(final String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(name -> new Player(new Name(name.strip())))
                .collect(Collectors.toList());
    }

    public Dealer getDealer(){
        return this.dealer;
    }
    public List<Player>  getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
}
