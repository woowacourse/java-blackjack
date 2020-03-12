package domain.result;

import java.util.Objects;

public abstract class BlackJackResult<T> {
    private final String name;

    public BlackJackResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public T getWinLose();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackJackResult<?> that = (BlackJackResult<?>) o;
        return Objects.equals(name, that.name) && Objects.equals(getWinLose(), that.getWinLose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
