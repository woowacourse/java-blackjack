package domain.player;

import domain.card.Card;

import java.util.List;

public class PlayerReadOnly {
    private final Player player;

    public PlayerReadOnly(Player player) {
        this.player = player;
    }

    public static PlayerReadOnly from(Player player) {
        return new PlayerReadOnly(player);
    }

    public boolean isBust() {
        return player.isBust();
    }

    public int getTotalScore() {
        return player.getScore();
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public String getName() {
        return player.getName();
    }

    public Card getFirstCard() {
        return player.getFirstCard();
    }

    @Override
    public String toString() {
        return "PlayerReadOnly{" +
                "player=" + player +
                '}';
    }
}
