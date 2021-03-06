package blackjack.domain.player.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerDTO {
    private final Name name;
    private final List<Card> cards;
    private final int value;

    public PlayerDTO(Player player) {
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