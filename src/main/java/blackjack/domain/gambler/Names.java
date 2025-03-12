package blackjack.domain.gambler;

import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static blackjack.domain.game.Round.MAX_PLAYER_COUNT;
import static blackjack.domain.game.Round.MIN_PLAYER_COUNT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Names {
    private static final String INVALID_PLAYER_COUNT = "플레이어 명수는 최소 1명, 최대 6명까지다.";
    private static final String NAME_CANNOT_BE_DUPLICATED = "플레이어의 이름은 중복 불가하다.";
    private static final String NAME_CANNOT_BE_EQUAL_DEALER_NAME = "'딜러'라는 이름은 사용이 불가능 하다.";

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
            throw new IllegalArgumentException(INVALID_PLAYER_COUNT);
        }
    }

    private void validateDuplicatedNames(final List<Name> names) {
        Set<Name> removedDuplicatedNames = new HashSet<>(names);
        if (removedDuplicatedNames.size() != names.size()) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_DUPLICATED);
        }
    }

    private void validateEqualsDealerName(final List<Name> names) {
        if (names.contains(DEALER_NAME)) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_EQUAL_DEALER_NAME);
        }
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
