package blackjack.domain.player;

import blackjack.domain.card.Hand;

import java.util.Objects;

public class Player extends Participant {

    private static final int HIT_THRESHOLD = 21;

    private final PlayerName playerName;

    public Player(Hand hand, PlayerName playerName) {
        super(hand);
        this.playerName = playerName;
    }

    @Override
    protected boolean canHit(int score) {
        return score <= HIT_THRESHOLD;
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

    public String getPlayerName() {
        return playerName.getValue();
    }
}
