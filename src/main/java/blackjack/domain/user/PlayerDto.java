package blackjack.domain.user;

import blackjack.domain.card.Card;
import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<Card> cards;

    public PlayerDto(User player) {
        this.name = player.getName();
        this.cards = player.getCards();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
