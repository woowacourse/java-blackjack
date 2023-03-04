package domain.player;

import domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerReadOnly {
    private final Player player;

    public PlayerReadOnly(Player player) {
        this.player = player;
    }

    public static PlayerReadOnly from(Player player) {
        return new PlayerReadOnly(player);
    }

    public int getTotalScore() {
        return player.getTotalScore();
    }

    public List<Card> getCards() {
        return player.getCards()
                .stream().collect(Collectors.toUnmodifiableList());
    }

    public String getName() {
        return player.getName();
    }
}
