package blackjack.domain.card.strategy;

import static blackjack.domain.card.Suit.HEART;
import static java.util.stream.Collectors.toCollection;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import java.util.Arrays;
import java.util.LinkedList;

public class FixedCardsGenerateStrategy implements CardsGenerateStrategy {

    private LinkedList<Card> cards = new LinkedList<>();

    public FixedCardsGenerateStrategy(Denomination... denominations) {
        LinkedList<Card> cards = Arrays.stream(denominations)
                .map(denomination -> new Card(denomination, HEART))
                .collect(toCollection(LinkedList::new));
        this.cards = cards;
    }

    @Override
    public LinkedList<Card> generate() {
        return cards;
    }
}
