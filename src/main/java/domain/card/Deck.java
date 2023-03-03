package domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private static final String ERROR_EMPTY_CARDS = "[ERROR] 뽑을 수 있는 카드가 존재하지 않습니다";

    private final Queue<Card> cards;

    private Deck(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        List<Card> initialCards = generateCards();
        Collections.shuffle(initialCards);

        return new Deck(new LinkedList<>(initialCards));
    }

    private static List<Card> generateCards() {
        return Arrays.stream(Denomination.values())
                .flatMap(denomination -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(denomination, suit)))
                .collect(Collectors.toList());
    }

    public Card pollAvailableCard() {
        if (cards.size() == 0) {
            throw new IllegalStateException(ERROR_EMPTY_CARDS);
        }

        return cards.poll();
    }

    public List<Card> pollTwoCards() {
        return List.of(pollAvailableCard(), pollAvailableCard());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList((LinkedList<Card>) cards);
    }
}
