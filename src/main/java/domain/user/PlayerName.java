package domain.user;

public class PlayerName {

    private static final String NAME_LENGTH_EXCEED_MESSAGE = "[ERROR] 허용 가능한 이름 크기를 벗어났습니다.";
    private static final String NAME_IS_RESERVED_MESSAGE = "[ERROR] 플레이어 이름은 예약어가 될 수 없습니다.";

    private static final int NAME_MIN_LENGTH = 1;
    private static final int NAME_MAX_LENGTH = 5;

    private final String name;

    public PlayerName(final ReservedRole role) {
        this.name = role.getName();
    }

    public PlayerName(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String target) {
        validateLength(target);
        validateIsReserved(target);
    }

    private void validateLength(final String target) {
        if (target.length() < NAME_MIN_LENGTH || target.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_EXCEED_MESSAGE);
        }
    }

    private void validateIsReserved(final String target) {
        if (ReservedRole.hasRoleSameNameWith(target)) {
            throw new IllegalArgumentException(NAME_IS_RESERVED_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
