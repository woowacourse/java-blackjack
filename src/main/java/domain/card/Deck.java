package domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private static final String ERROR_EMPTY_CARDS = "[ERROR] 뽑을 수 있는 카드가 존재하지 않습니다";

    private final Queue<Card> cards = new LinkedList<>();

    private Deck() {
    }

    public static Deck create() {
        Deck deck = new Deck();

        List<Card> initialCards = generateCards();
        Collections.shuffle(initialCards);
        deck.cards.addAll(initialCards);

        return deck;
    }

    private static List<Card> generateCards() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(rank, suit)))
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public Card pollAvailableCard() {
        if (cards.size() == 0) {
            throw new IllegalStateException(ERROR_EMPTY_CARDS);
        }

        return cards.poll();
    }
}
