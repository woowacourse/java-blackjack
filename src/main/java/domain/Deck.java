package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final String ERROR_DRAW_CARD_COUNT = "Deck 카드 Draw 오류. 남은 카드수: %d, Draw 카드 수: %d";
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Deck create() {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            Arrays.stream(Rank.values())
                    .forEach(rank -> cards.add(new Card(rank, suit)));
        }

        return new Deck(cards);
    }

    public Card draw() {
        validateCardsCount();
        Card card = cards.getLast();
        cards.removeLast();

        return card;
    }

    public List<Card> draw(int count) {
        validateCardsCount(count);
        List<Card> drawnCards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            drawnCards.add(draw());
        }

        return List.copyOf(drawnCards);
    }

    private void validateCardsCount(int drawCount) {
        if (cards.size() < drawCount) {
            String message = String.format(ERROR_DRAW_CARD_COUNT, cards.size(), drawCount);
            throw new IllegalArgumentException(message);
        }
    }

    private void validateCardsCount() {
        if (cards.isEmpty()) {
            String message = String.format(ERROR_DRAW_CARD_COUNT, 0, 1);
            throw new IllegalArgumentException(message);
        }
    }

    public Deck shuffle(DeckShuffler shuffler) {
        List<Card> shuffledCards = shuffler.shuffle(Collections.unmodifiableList(cards));

        return new Deck(shuffledCards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
