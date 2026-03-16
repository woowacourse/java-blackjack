package domain.participant;

import static exception.ErrorMessage.DUPLICATE_DEALER_NAME;
import static exception.ErrorMessage.DUPLICATE_PLAYER_NAME;
import static exception.ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {

    public static final int MINIMUM_BOUND = 1;
    public static final int MAXIMUM_BOUND = 5;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final List<Player> players) {
        validatePlayerCounts(players);
        validateSameAsDealerName(players);
        validateDuplicateName(players);

        this.players = new ArrayList<>(players);
        this.dealer = new Dealer();
    }


    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }


    private void validatePlayerCounts(final List<Player> players) {
        if (players.size() < MINIMUM_BOUND || players.size() > MAXIMUM_BOUND) {
            throw new IllegalArgumentException(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateSameAsDealerName(final List<Player> players) {
        for (final Player player : players) {
            if (Dealer.DEALER_NAME.equals(player.getName())) {
                throw new IllegalArgumentException(DUPLICATE_DEALER_NAME.getMessage());
            }
        }
    }

    private void validateDuplicateName(final List<Player> players) {
        final Set<String> names = new HashSet<>();

        for (final Player player : players) {
            names.add(player.getName());
        }

        if (names.size() != players.size()) {
            throw new IllegalArgumentException(DUPLICATE_PLAYER_NAME.getMessage());
        }
    }
}
