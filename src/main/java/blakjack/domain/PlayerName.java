package blakjack.domain;

import static blakjack.domain.participant.Dealer.DEALER_NAME;

public final class PlayerName {
    private static final String INVALID_MESSAGE = "딜러라는 이름은 사용할 수 없습니다";

    private final String value;

    public PlayerName(final String name) {
        if (DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(INVALID_MESSAGE);
        }
        this.value = name;
    }

    public String getValue() {
        return value;
    }
}
