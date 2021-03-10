package blackjack.domain.card;

import java.util.*;

public class CardGenerator {

    public static ArrayDeque<Card> makeShuffledNewDeck() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Suit.values())
            .forEach(suit -> matchDenomination(cards, suit));
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    private static void matchDenomination(List<Card> cards, Suit suit) {
        Arrays.stream(Denomination.values())
            .forEach(denomination -> cards.add(new Card(denomination, suit)));
    }

}
