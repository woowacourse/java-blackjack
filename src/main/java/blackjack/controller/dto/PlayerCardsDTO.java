package blackjack.controller.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerCardsDTO {
    private final Name name;
    private final List<Card> cards;

    public PlayerCardsDTO(Player player) {
        name = player.getName();
        cards = new ArrayList<>(player.getCards());
    }

    public PlayerCardsDTO(Name name, List<Card> cards) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards;
    }
}
