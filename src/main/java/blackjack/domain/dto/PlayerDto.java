package blackjack.domain.dto;

import blackjack.domain.Name;
import blackjack.domain.card.Card;
import java.util.List;

public class PlayerDto {

    private final Name name;
    private final List<Card> cards;

    public PlayerDto(final Name name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
