package domain.card;

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
                .filter(CardDeck::removeAnotherAce)
                .toList();
    }

    private static boolean removeAnotherAce(CardNumber createNumber) {
        return createNumber != CardNumber.ACE_ANOTHER;
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
        validateEmptyCardDeck();
        return deck.removeLast();
    }

    private void validateEmptyCardDeck() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("카드 덱이 비었습니다.");
        }
    }

    public List<Card> getDeck() {
        return deck;
    }
}
