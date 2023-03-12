package domain.people;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import view.ErrorMessage;

public final class Participants {
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private final Dealer dealer;
    private final List<Player> players;

    private Participants(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants from(final List<String> names) {
        validate(names);
        List<Player> participants = new ArrayList<>();

        for (String name : names) {
            participants.add(new Player(name));
        }
        return new Participants(new Dealer(), participants);
    }

    private static void validate(final List<String> names) {
        validateNumberOfNames(names);
        validateNoDuplication(names);
    }
    
    private static void validateNumberOfNames(final List<String> names) {
        if (names.size() < MINIMUM_PLAYER_COUNT || names.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_OF_PLAYER.getMessage());
        }
    }

    private static void validateNoDuplication(final List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_DUPLICATED.getMessage());
        }
    }

    public Optional<Player> findPlayer(final String participantName) {
        return players.stream().
            filter(player -> player.fetchPlayerName().equals(participantName)).
            findAny();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
