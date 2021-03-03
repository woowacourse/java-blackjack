package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class PlayerDto {
    private final Name name;
    private final List<Card> cards;
    private final int value;

    public PlayerDto(Player player) {
        name = player.getName();
        cards = new ArrayList<>(player.getCards());
        value = player.getValue();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getValue() {
        return value;
    }
}