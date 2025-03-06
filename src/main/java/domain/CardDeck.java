package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    public static final int DRAW_COUNT_WHEN_START = 2;

    private final List<Card> deck;

    public static CardDeck createCards(CardShuffler cardShuffler) {
        List<Card> cards = Arrays.stream(Pattern.values())
                .flatMap(pattern -> createCardNumbers().stream()
                        .map(cardNumber -> new Card(pattern, cardNumber)))
                .collect(Collectors.toList());
        List<Card> shuffleCards = cardShuffler.shuffle(cards);
        return new CardDeck(shuffleCards);
    }

    private static List<CardNumber> createCardNumbers() {
        return Arrays.stream(CardNumber.values())
                .filter(createNumber -> createNumber != CardNumber.ACE_ANOTHER)
                .toList();
    }

    private CardDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> shuffle(CardShuffler cardShuffler) {
        return cardShuffler.shuffle(this.deck);
    }

    public List<Card> drawCardWhenStart() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < DRAW_COUNT_WHEN_START; i++) {
            cards.add(drawCard());
        }
        return cards;
    }

    public Card drawCard() {
        return deck.removeLast();
    }

    public List<Card> getDeck() {
        return deck;
    }
}
