package domain.model;

import domain.vo.Batting;
import java.util.Objects;

public class Player extends Participant {

    private final String name;
    private final Batting batting;

    public Player(final Cards cards, final String name, final Batting batting) {
        super(cards);
        this.name = name;
        this.batting = batting;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public String getName() {
        return name;
    }

    public Batting getBatting() {
        return batting;
    }
}
