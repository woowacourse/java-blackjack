package blackjack.domain.gambler;

import static blackjack.domain.game.Round.MAX_PLAYER_COUNT;
import static blackjack.domain.game.Round.MIN_PLAYER_COUNT;
import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static blackjack.view.ErrorMessage.INVALID_PLAYER_COUNT;
import static blackjack.view.ErrorMessage.NAME_CANNOT_BE_DUPLICATED;
import static blackjack.view.ErrorMessage.NAME_CANNOT_BE_EQUAL_DEALER_NAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Names {
    private final List<Name> names;

    public Names(final List<Name> names) {
        validateDuplicatedNames(names);
        validateEqualsDealerName(names);
        validatePlayersCount(names);
        this.names = new ArrayList<>(names);
    }

    private void validatePlayersCount(final List<Name> names) {
        int playersCount = names.size();
        if (playersCount > MAX_PLAYER_COUNT || playersCount < MIN_PLAYER_COUNT) {
            throw new IllegalArgumentException(INVALID_PLAYER_COUNT.getMessage());
        }
    }

    private void validateDuplicatedNames(final List<Name> names) {
        Set<Name> removedDuplicatedNames = new HashSet<>(names);
        if (removedDuplicatedNames.size() != names.size()) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_DUPLICATED.getMessage());
        }
    }

    private void validateEqualsDealerName(final List<Name> names) {
        if (names.contains(DEALER_NAME)) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_EQUAL_DEALER_NAME.getMessage());
        }
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
