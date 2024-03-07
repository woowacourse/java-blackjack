package blackjack.domain.participant;

import blackjack.domain.Card;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player extends Participant {

    private final Name name;

    Player(List<Card> cards, Name name) {
        super(cards);
        this.name = name;
    }

    public static Player from(String name) {
        return new Player(Collections.emptyList(), new Name(name));
    }

    @Override
    protected int getMaxDrawableScore() {
        return BLACKJACK_SCORE;
    }

    public boolean isWin(Dealer dealer) {
        return !dealer.isWin(this);
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Player player = (Player) object;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
