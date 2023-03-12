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

    public int getTotalScore() {
        return player.getScore();
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public String getName() {
        return player.getName();
    }

    public boolean isBust() {
        return player.isBust();
    }

    public Card getCardIndexOf(int index) {
        return player.getCardIndexOf(index);
    }
}
