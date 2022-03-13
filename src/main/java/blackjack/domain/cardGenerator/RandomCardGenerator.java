package blackjack.domain.cardGenerator;

import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class RandomCardGenerator implements CardGenerator {
    static final Stack<PlayingCard> cardDeck;

    static {
        cardDeck = Arrays.stream(Suit.values())
            .flatMap(suit -> Arrays.stream(Denomination.values())
                .map(denomination -> PlayingCard.of(suit, denomination)))
            .collect(Collectors.toCollection(Stack::new));
    }

    public Stack<PlayingCard> generate() {
        return getCopiedDeck();
    }

    private Stack<PlayingCard> getCopiedDeck() {
        final Stack<PlayingCard> copedDeck = new Stack<>();
        copedDeck.addAll(cardDeck);
        return copedDeck;
    }
}
