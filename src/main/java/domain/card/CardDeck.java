package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CardDeck {

    public static final int DRAW_COUNT_WHEN_START = 2;

    private final List<Card> deck;

    public static CardDeck createCards(CardShuffler cardShuffler) {
        List<Card> cards = generateAllCards();
        List<Card> shuffledCards = cardShuffler.shuffle(cards);
        return new CardDeck(shuffledCards);
    }

    private static List<Card> generateAllCards() {
        return Arrays.stream(Pattern.values())
                .flatMap(CardDeck::generateCardsForPattern)
                .toList();
    }

    private static Stream<Card> generateCardsForPattern(Pattern pattern) {
        return getCardNumbersWithoutAnotherAce().stream()
                .map(cardNumber -> new Card(pattern, cardNumber));
    }

    private static List<CardNumber> getCardNumbersWithoutAnotherAce() {
        return Arrays.stream(CardNumber.values())
                .filter(CardDeck::isNotAnotherAce)
                .toList();
    }

    private static boolean isNotAnotherAce(CardNumber cardNumber) {
        return cardNumber != CardNumber.ACE_ANOTHER;
    }

    private CardDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> drawCardWhenStart() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < DRAW_COUNT_WHEN_START; i++) {
            cards.add(drawCard());
        }
        return cards;
    }

    public Card drawCard() {
        validateEmptyDeck();
        return deck.removeLast();
    }

    private void validateEmptyDeck() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 카드 덱이 비어있습니다.");
        }
    }

    public List<Card> getDeck() {
        return deck;
    }
}
