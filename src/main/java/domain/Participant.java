package domain;

import java.util.Objects;

public class Participant {
    private final String name;
    private final Cards cards;

    public Participant(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Participant that = (Participant) object;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
