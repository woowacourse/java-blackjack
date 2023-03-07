package blackjack.domain.participant;

import java.util.Objects;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        super();
        this.name = name;
    }

    @Override
    public boolean canReceive() {
        Score score = calculateScore();
        Score blackJackScore = new Score(BLACK_JACK_SCORE);
        return score.isLessThan(blackJackScore);
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
