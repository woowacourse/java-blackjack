package blackjack.domain.user;

import blackjack.exception.NeedRetryException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlayerNames {

    private final List<UserName> names;

    public PlayerNames(final List<UserName> names) {
        validateDuplicate(names);
        validateDateDealerName(names);
        this.names = names;
    }

    private void validateDuplicate(final List<UserName> userNames) {
        if (Set.copyOf(userNames).size() != userNames.size()) {
            throw new NeedRetryException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    private void validateDateDealerName(final List<UserName> names) {
        if (names.contains(new UserName(Dealer.DEALER_NAME))) {
            throw new NeedRetryException(Dealer.DEALER_NAME + "를 이름으로 사용할 수 없습니다.");
        }
    }

    public List<UserName> getNames() {
        return Collections.unmodifiableList(names);
    }
}
