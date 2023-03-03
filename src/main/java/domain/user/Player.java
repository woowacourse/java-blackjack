package domain.user;

import domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player extends User {
    private final Name name;
    private PlayerStatus status = PlayerStatus.NORMAL;
    private boolean isWinner = false;

    public Player(Name name, List<Card> firstTurnCards) {
        super(firstTurnCards);
        this.name = name;
    }

    @Override
    public void win() {
        isWinner = true;
    }

    @Override
    public void lose() {
        isWinner = false;
    }

    @Override
    protected void checkBustByScore() {
        if (score.getScore() > BLACKJACK) {
            status = PlayerStatus.BUST;
        }
    }

    @Override
    public UserStatus getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return name.getName();
    }

    public boolean isWinner() {
        return isWinner;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Player player = (Player) other;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
