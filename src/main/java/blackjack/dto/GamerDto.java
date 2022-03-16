package blackjack.dto;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;

public class GamerDto {

    private final String name;
    private final List<Card> cards;
    private final int cardNumberSum;

    public GamerDto(Gamer gamer) {
        this.name = gamer.getName();
        this.cards = List.copyOf(gamer.getCards());
        this.cardNumberSum = gamer.getCardsNumberSum();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int getCardNumberSum() {
        return cardNumberSum;
    }
}
