package blackjack.domain.gamer;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player extends BlackjackGamer {

    private final Name name;

    public Player(String name) {
        super();
        this.name = new Name(name);
    }

    public Player(String name, List<Card> cards) {
        super(cards);
        this.name = new Name(name);
    }

    @Override
    public boolean canReceiveCard() {
        return !isBlackjack() && getScore() <= BlackjackConstants.BLACKJACK_VALUE.getValue();
    }

    @Override
    public String getName() {
        return name.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
