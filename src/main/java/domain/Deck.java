package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Deck {

    private static final int INITIAL_DECK_COUNT = 52;
    private final List<TrumpCard> cards;

    public Deck(List<TrumpCard> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<TrumpCard> cards) {
        validateNotNull(cards);
        validateSize(cards);
        validateNotDuplicate(cards);
    }

    private void validateNotNull(List<TrumpCard> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("덱은 카드를 가지고 있어야합니다.");
        }
    }

    private void validateSize(List<TrumpCard> cards) {
        if (cards.size() != INITIAL_DECK_COUNT) {

            throw new IllegalArgumentException("덱의 크기는 52여야 합니다.");
        }
    }

    private void validateNotDuplicate(List<TrumpCard> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("덱에 중복된 카드가 있습니다.");
        }
    }

    public static Deck create() {
        List<TrumpCard> cards = initializeCards();
        Deck deck = new Deck(cards);
        deck.shuffle();
        return deck;
    }

    private static List<TrumpCard> initializeCards() {
        List<TrumpCard> trumpCard = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                trumpCard.add(new TrumpCard(rank, suit));
            }
        }
        return trumpCard;
    }

    public TrumpCard draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 없습니다.");
        }

        return cards.removeLast();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<TrumpCard> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deck deck = (Deck) o;
        return Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}

