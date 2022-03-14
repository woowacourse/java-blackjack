package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {

    private static final List<Card> cards = new ArrayList<>();

    private final CardPickMachine cardPickMachine;

    static {
        for (Suit suit : Suit.values()) {
            Arrays.stream(Symbol.values())
                    .forEach(symbol -> cards.add(new Card(suit, symbol)));
        }
     }

    public Cards(CardPickMachine cardPickMachine) {
        this.cardPickMachine = cardPickMachine;
    }

    public Card assignCard() {
        return cards.get(cardPickMachine.assignIndex());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cards cards1 = (Cards) o;

        return cards != null ? cards.equals(cards1.cards) : cards1.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
