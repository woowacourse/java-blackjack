package blackjack.domain.card;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CardDeckFactory {
    private static final List<Card> cards = new LinkedList<>();

    static {
        for (CardLetter letter : CardLetter.values()) {
            Arrays.stream(CardSuit.values())
                    .map(suit -> new Card(letter, suit))
                    .forEach(cards::add);
        }
    }

    public static CardDeck make(ShuffleStrategy shuffleStrategy) {
        return new CardDeck(shuffleStrategy.shuffle(cards));
    }
}
