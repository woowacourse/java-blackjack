package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final List<Card> deck;
    private final Deque<Card> cards;

    static {
        List<Card> cardCombination = new ArrayList<>();
        Arrays.stream(Suit.values())
            .forEach(suit -> Arrays.stream(Denomination.values())
                .forEach(denomination -> cardCombination.add(new Card(denomination, suit))
                )
            );
        deck = cardCombination;
    }

    public Deck() {
        this.cards = new ArrayDeque<>(shuffleCards());
    }

    public List<Card> shuffleCards() {
        List<Card> cards = new ArrayList<>(deck);
        Collections.shuffle(cards);
        return new ArrayList<>(cards);
    }

    public List<Card> pickInitialCards() {
        return Stream.<Card>builder()
            .add(cards.pop()).add(cards.pop())
            .build()
            .collect(Collectors.toList());
    }

    public Card pickSingleCard() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
