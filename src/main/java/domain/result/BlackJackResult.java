package domain.result;

import java.util.Objects;

public abstract class BlackJackResult<T> {
    private final String gamerName;

    BlackJackResult(String gamerName) {
        this.gamerName = gamerName;
    }

    abstract public T getWinLose();

    String getGamerName() {
        return gamerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackJackResult<?> that = (BlackJackResult<?>) o;
        return Objects.equals(gamerName, that.gamerName) && Objects.equals(getWinLose(), that.getWinLose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gamerName);
    }
}
