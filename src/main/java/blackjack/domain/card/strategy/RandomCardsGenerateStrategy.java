package blackjack.domain.card.strategy;

import static java.util.stream.Collectors.toCollection;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomCardsGenerateStrategy implements CardsGenerateStrategy {

    @Override
    public LinkedList<Card> generate() {
        LinkedList<Card> cards = makeCards();
        shuffleCards(cards);
        return cards;
    }

    public static LinkedList<Card> makeCards() {
        return Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(denomination, suit)))
                .collect(toCollection(LinkedList::new));
    }

    public void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
