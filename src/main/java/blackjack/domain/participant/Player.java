package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.participant.rule.DrawRule;
import blackjack.domain.participant.rule.PlayerRule;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player extends Participant {

    private static final int START_CARD_SIZE = 2;
    private static final DrawRule<Player> PLAYER_RULE = new PlayerRule();

    private final Name name;

    public Player(List<Card> cards, Name name) {
        super(cards);
        this.name = Objects.requireNonNull(name);
    }

    public static Player from(String name) {
        return new Player(Collections.emptyList(), new Name(name));
    }

    @Override
    protected int getMaxDrawableScore() {
        return BLACKJACK_SCORE;
    }

    @Override
    protected int getStartCardSize() {
        return START_CARD_SIZE;
    }

    @Override
    protected DrawRule<Player> getRules() {
        return PLAYER_RULE;
    }

    public Name getName() {
        return name;
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
