package blackjack.domain.user;

public final class PlayerName extends Name {

    static final String NAME_IS_DEALER_NAME_EXCEPTION_MESSAGE = "플레이어의 이름은 " + DealerName.DEALER_NAME + "일 수 없습니다.";

    public PlayerName(final String name) {
        super(name);
        validateIsDealerName(name);
        validateBlank(name);
    }

    private void validateIsDealerName(final String name) {
        if (DealerName.DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(NAME_IS_DEALER_NAME_EXCEPTION_MESSAGE);
        }
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_EXCEPTION_MESSAGE);
        }
    }
}
