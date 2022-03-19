package blackjack.domain.entry.vo;

import blackjack.domain.entry.Dealer;
import java.util.Objects;

public class Name {

    public static final Name DEALER = new Name(Dealer.NAME);

    private final String value;

    public Name(String value) {
        validateName(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(value, name1.value);
    }

    private void validateName(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백이 될 수 없습니다.");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
