package blackjack.domain.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Deck {
    private static final Random RANDOM = new Random();

    private final List<Card> cards;

    public Deck() {
        this.cards = addAllCard();
    }

    public Card pickRandomCard() {
        validateDeck();
        int pickCardIndex = RANDOM.nextInt(cards.size());
        return cards.remove(pickCardIndex);
    }

    private List<Card> addAllCard() {
        List<Card> allCards = new ArrayList<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> allCards.addAll(addAllShape(rank)));
        return allCards;
    }

    private List<Card> addAllShape(Rank rank) {
        List<Card> shapeCards = new ArrayList<>();
        Arrays.stream(Shape.values())
                .map(shape -> new Card(shape, rank))
                .forEach(shapeCards::add);
        return shapeCards;
    }

    private void validateDeck() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 없습니다.");
        }
    }

    public int size() {
        return cards.size();
    }
}
