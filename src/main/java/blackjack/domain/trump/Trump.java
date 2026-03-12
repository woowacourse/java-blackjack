package blackjack.domain.trump;

import blackjack.utils.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Trump {

    private final List<Card> deck;
    private final SortBehavior sortBehavior;

    public Trump(final SortBehavior sortBehavior) {
        this.sortBehavior = sortBehavior;
        deck = generateDeck();
    }

    private List<Card> generateDeck() {
        final List<Card> shuffledDeck = Lists.cartesianProduct(List.of(Suit.values()),
                List.of(Denomination.values()))
            .stream()
            .map(pair -> new Card(pair.getLeft(), pair.getRight()))
            .collect(Collectors.toCollection(ArrayList::new));
        sortBehavior.shuffle(shuffledDeck);

        return shuffledDeck;
    }

    public Card draw() {
        return deck.removeLast();
    }
}
