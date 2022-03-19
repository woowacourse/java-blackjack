package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class CardDeck {

    private static final int INIT_PROVIDING_CARD_SIZE = 2;

    private final Queue<Card> deck;

    public CardDeck() {
        List<Card> allCards = generateDeck();
        Collections.shuffle(allCards);
        deck = new LinkedList<>(allCards);
    }

    private List<Card> generateDeck() {
        return CardPattern.allPatterns()
                .stream()
                .map(this::createPatternCards)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Card> createPatternCards(final CardPattern pattern) {
        return CardNumber.allNumbers()
                .stream()
                .map(number -> Card.of(pattern, number))
                .collect(Collectors.toList());
    }

    public List<Card> provideInitCards() {
        validateEnoughDeckSize();
        return Arrays.asList(deck.poll(), deck.poll());
    }

    private void validateEnoughDeckSize() {
        if (deck.size() < INIT_PROVIDING_CARD_SIZE) {
            throw new IllegalStateException("초기 지급 카드 개수 이상의 카드가 없습니다.");
        }
    }

    public Card provideCard() {
        validateEmptyDeck();
        return deck.poll();
    }

    private void validateEmptyDeck() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("남은 카드가 없습니다.");
        }
    }
}
