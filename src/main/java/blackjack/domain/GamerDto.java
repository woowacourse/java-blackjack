package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Name;

import java.util.List;

public class GamerDto {
    public static final int FIRST_INDEX = 0;
    private final Name name;
    private final List<Card> cards;
    private final int cardNumberSum;

    public GamerDto(Gamer gamer) {
        this.name = gamer.getName();
        this.cards = List.copyOf(gamer.getCards());
        this.cardNumberSum = sumOfCards();
    }

    private int sumOfCards() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
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

    public int getCardNumberSum() {
        return cardNumberSum;
    }
}
