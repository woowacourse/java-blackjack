package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;

import java.util.List;

public class PlayerNameCardsResponse {

    private final String name;
    private final List<Card> cards;

    public PlayerNameCardsResponse(Player player) {
        this.name = player.getName();
        this.cards = player.getCards().getCards();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
