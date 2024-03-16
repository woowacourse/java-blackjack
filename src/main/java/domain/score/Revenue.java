package domain.score;

import java.util.Objects;

public class Revenue {

    private final int value;

    public Revenue(int value) {
        this.value = value;
    }

    public int getAmount() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revenue revenue = (Revenue) o;
        return value == revenue.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
