package domain.card;

import java.util.*;

public class CardDeck {
    private static final String CONSUME_ALL_CARD_DECK_ERROR_MESSAGE = "[ERROR] 카드를 다 뽑았습니다.";
    private static final String DUPLICATED_CARD_ERROR_MESSAGE = "[ERROR] 중복된 항목이 존재합니다.";
    private static final String EMPTY_LIST_ERROR_MESSAGE = "[ERROR] 리스트가 비어있습니다.";
    private final Deque<Card> deck;

    public CardDeck() {
        List<Card> initialCards = new ArrayList<>();
        init(initialCards);
        validate(initialCards);
        shuffle(initialCards);
        this.deck = new ArrayDeque<>(initialCards);
    }

    private void init(List<Card> initialCard) {
        for (Pattern pattern : Pattern.values()) {
            for (Rank rank : Rank.values()) {
                initialCard.add(new Card(rank, pattern));
            }
        }

        //위아래중 무엇이 나은지?
//                Arrays.stream(Pattern.values())
//                        .flatMap(pattern -> Arrays.stream(Rank.values())
//                        .map(rank -> new Card(rank, pattern)))
//                        .forEach(deck::push);
    }

    private void shuffle(List<Card> initialCards) {
        Collections.shuffle(initialCards);
    }

    public int getDeckSize() {
        return deck.size();
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException(CONSUME_ALL_CARD_DECK_ERROR_MESSAGE);
        }
        return deck.pop();
    }

    public List<Card> getCardDeck() {
        List<Card> copyDeck = new ArrayList<>(this.deck);
        return Collections.unmodifiableList(copyDeck);
    }

    private void validate(List<Card> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_LIST_ERROR_MESSAGE);
        }
        if (hasDuplicates(items)) {
            throw new IllegalArgumentException(DUPLICATED_CARD_ERROR_MESSAGE);
        }
    }

    private boolean hasDuplicates(List<Card> items) {
        return items.stream().distinct().count() != items.size();
    }
}
