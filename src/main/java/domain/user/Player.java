package domain.user;

import domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player extends User {
    private final Name name;
    private PlayerStatus status = PlayerStatus.NORMAL;
    private boolean isWinner = false;

    public Player(Name name) {
        super();
        this.name = name;
    }

    @Override
    public void receiveCard(Card card) {
        super.receiveCard(card);
        status = score.calculatePlayerStatus();
    }

    @Override
    public void receiveCards(List<Card> receivedCards) {
        super.receiveCards(receivedCards);
        status = score.calculatePlayerStatus();
    }

    @Override
    public void win(User dealer) {
        isWinner = true;
        dealer.lose();
    }

    @Override
    public void lose() {
        isWinner = false;
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
