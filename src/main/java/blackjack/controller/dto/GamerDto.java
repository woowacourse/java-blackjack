package blackjack.controller.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Name;

import java.util.List;

public class GamerDto {

    public static final int FIRST_CARD_INDEX = 0;

    private final Name name;
    private final List<Card> cards;
    private final int cardNumberSum;

    public GamerDto(Gamer gamer) {
        this.name = gamer.getName();
        this.cards = List.copyOf(gamer.getCards());
        this.cardNumberSum = gamer.getCardsNumberSum();
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
        return cards.get(FIRST_CARD_INDEX);
    }

    public int getCardNumberSum() {
        return cardNumberSum;
    }
}
