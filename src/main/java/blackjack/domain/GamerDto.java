package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Name;

import java.util.List;

public class GamerDto {
    public static final int FIRST_INDEX = 0;
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

    public int getCardSize() {
        return cards.size();
    }

    public Card getFirstCard() {
        return cards.get(FIRST_INDEX);
    }
}
