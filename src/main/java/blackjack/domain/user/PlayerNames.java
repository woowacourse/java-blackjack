package blackjack.domain.user;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlayerNames {

    private final List<UserName> names;

    public PlayerNames(List<UserName> names) {
        validateDuplicate(names);
        validateDateDealerName(names);
        this.names = names;
    }

    private void validateDuplicate(List<UserName> userNames) {
        if (Set.copyOf(userNames).size() != userNames.size()) {
            throw new IllegalArgumentException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    private void validateDateDealerName(List<UserName> names) {
        if (names.contains(new UserName(Dealer.DEALER_NAME))) {
            throw new IllegalArgumentException(Dealer.DEALER_NAME + "를 이름으로 사용할 수 없습니다.");
        }
    }

    public List<UserName> getNames() {
        return Collections.unmodifiableList(names);
    }
}
