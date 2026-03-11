package domain.participant;

import static constant.GameRule.MAX_NAME_LENGTH;
import static constant.GameRule.MIN_NAME_LENGTH;
import static message.ErrorMessage.PLAYER_NAME_OUT_OF_RANGE;

import java.util.Objects;

public class Name {
    private final String name;

    public Name(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_OUT_OF_RANGE.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(getName(), name1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
