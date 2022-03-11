package blackjack.model.player;

import java.util.Objects;
import java.util.UUID;

public class Name {

    private final UUID id;
    private final String value;

    public Name(String value) {
        this.value = value;
        this.id = UUID.randomUUID();
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(id, name.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
