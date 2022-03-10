package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Name;

import java.util.List;

public class GamerDto {
    private final Name name;
    private final List<Card> cards;

    public GamerDto(Gamer gamer) {
        this.name = gamer.getName();
        this.cards = List.copyOf(gamer.getCards());
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards;
    }
}
