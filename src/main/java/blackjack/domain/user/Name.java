package blackjack.domain.user;

import java.util.Objects;

import static blackjack.domain.user.Dealer.DEALER_NAME;

public class Name {
    private final String name;

    public Name(String name) {
        this.name = name;
        whenUserNameEmpty();
    }

    private void whenUserNameEmpty() {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름이 공백일 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name1 = (Name) o;
        return Objects.equals(getName(), name1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
