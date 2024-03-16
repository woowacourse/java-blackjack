package domain.player;

import static domain.player.Dealer.DEALER_NAME;

import java.util.List;
import java.util.Objects;

public class PlayerNames {

    static final String NAME_DUPLICATE_MESSAGE = "중복된 이름은 허용하지 않습니다.";
    static final String NAMES_SIZE_INVALID_MESSAGE = "참가자는 1명 이상 10명 이하 입니다.";
    static final String DEALER_NAME_MESSAGE = "딜러라는 이름은 사용할 수 없습니다.";
    private static final int MINIMUM_NAMES_SIZE = 1;
    private static final int MAXIMUM_NAMES_SIZE = 10;
    private final List<PlayerName> names;


    public PlayerNames(final List<String> names) {
        validateNotDealerName(names);
        validateDuplicate(names);
        validatePlayerNamesSize(names);

        this.names = names.stream()
                .map(PlayerName::new)
                .toList();
    }

    private void validateNotDealerName(final List<String> names) {
        if (names.contains(DEALER_NAME)) {
            throw new IllegalArgumentException(DEALER_NAME_MESSAGE);
        }
    }


    private void validateDuplicate(List<String> names) {
        boolean isDuplicated = names.stream().distinct().count() != names.size();

        if (isDuplicated) {
            throw new IllegalArgumentException(NAME_DUPLICATE_MESSAGE);
        }
    }

    private void validatePlayerNamesSize(List<String> names) {
        boolean isValidateLength =
                MINIMUM_NAMES_SIZE <= names.size() && names.size() <= MAXIMUM_NAMES_SIZE;

        if (!isValidateLength) {
            throw new IllegalArgumentException(NAMES_SIZE_INVALID_MESSAGE);
        }
    }

    public List<PlayerName> names() {
        return names;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (PlayerNames) obj;
        return Objects.equals(this.names, that.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names);
    }

}
