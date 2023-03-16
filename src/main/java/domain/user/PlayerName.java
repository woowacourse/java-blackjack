package domain.user;

import static domain.user.Playable.DEALER_NAME;

import java.util.Objects;

public class PlayerName {
    
    public static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 1자 이상 5자 이하여야 합니다.";
    private static final String EMPTY_NAME_ERROR_MESSAGE = "이름은 공백일 수 없습니다.";
    private static final String INVALID_DEALER_NAME_USAGE_ERROR_MESSAGE = "플레이어의 이름은 딜러가 될 수 없습니다.";
    private final String value;
    
    public PlayerName(final String name) {
        this.validateBlank(name);
        this.validateDealerName(name);
        this.validateNameLength(name);
        this.value = name;
    }
    
    private void validateBlank(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_ERROR_MESSAGE);
        }
    }
    
    private void validateDealerName(final String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(INVALID_DEALER_NAME_USAGE_ERROR_MESSAGE);
        }
    }
    
    private void validateNameLength(final String name) {
        if (name.length() > 5 || name.length() < 1) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MESSAGE);
        }
    }
    
    public String getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final PlayerName name = (PlayerName) o;
        return this.value.equals(name.value);
    }
}
