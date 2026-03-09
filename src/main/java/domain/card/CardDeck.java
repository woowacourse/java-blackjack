package domain.card;

import java.util.*;

public class CardDeck {
    private static final String CONSUME_ALL_CARD_DECK_ERROR_MESSAGE = "[ERROR] 카드를 다 뽑았습니다.";
    private static final String DUPLICATED_CARD_ERROR_MESSAGE = "[ERROR] 중복된 항목이 존재합니다.";
    private static final String EMPTY_LIST_ERROR_MESSAGE = "[ERROR] 리스트가 비어있습니다.";
    private final Deque<Card> deck;

    public CardDeck() {
        this.deck = new ArrayDeque<>();
        init();
        validate(deck);
        shuffle();
    }

    private void init() {
        Arrays.stream(Pattern.values())
                .flatMap(pattern -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, pattern)))
                .forEach(deck::push);
    }

    private void shuffle() {
        List<Card> shuffled = new ArrayList<>(this.deck);
        Collections.shuffle(shuffled);
        deck.clear();
        deck.addAll(shuffled);
    }

    public int getDeckSize() {
        return deck.size();
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new NoSuchElementException(CONSUME_ALL_CARD_DECK_ERROR_MESSAGE);
        }
        return deck.pop();
    }

    private void validate(Deque<Card> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_LIST_ERROR_MESSAGE);
        }
        if (hasDuplicates(items)) {
            throw new IllegalArgumentException(DUPLICATED_CARD_ERROR_MESSAGE);
        }
    }

    private boolean hasDuplicates(Collection<Card> items) {
        return items.stream().distinct().count() != items.size();
    }
}
