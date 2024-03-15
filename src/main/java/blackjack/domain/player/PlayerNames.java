package blackjack.domain.player;

import blackjack.exception.NeedRetryException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlayerNames {

    private final List<PlayerName> names;

    public PlayerNames(final List<PlayerName> names) {
        validateDuplicate(names);
        validateDateDealerName(names);
        this.names = names;
    }

    private void validateDuplicate(final List<PlayerName> playerNames) {
        if (Set.copyOf(playerNames).size() != playerNames.size()) {
            throw new NeedRetryException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    private void validateDateDealerName(final List<PlayerName> names) {
        if (names.contains(new PlayerName(Dealer.DEALER_NAME))) {
            throw new NeedRetryException(Dealer.DEALER_NAME + "를 이름으로 사용할 수 없습니다.");
        }
    }

    public List<PlayerName> getNames() {
        return Collections.unmodifiableList(names);
    }
}
