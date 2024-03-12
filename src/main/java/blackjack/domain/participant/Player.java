package blackjack.domain.participant;

import blackjack.domain.card.Hand;

import blackjack.domain.rule.Score;
import java.util.Objects;

public class Player extends Participant {

    private static final Score HIT_THRESHOLD = new Score(21);

    private final PlayerName playerName;

    public Player(PlayerName playerName) {
        this(new Hand(), playerName);
    }

    public Player(Hand hand, PlayerName playerName) {
        super(hand);
        this.playerName = playerName;
    }

    @Override
    public boolean canHit() {
        return getHandScore().canHit(HIT_THRESHOLD);
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
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }
}
