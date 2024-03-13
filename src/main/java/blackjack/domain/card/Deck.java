package blackjack.domain.card;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private static final Random RANDOM = new Random();

    private final List<Card> deck;

    public Deck() {
        this.deck = initDeck();
    }

    private List<Card> initDeck() {
        List<Card> result = new LinkedList<>();
        Arrays.stream(Rank.values())
                .forEach((rank) -> addAllShape(result, rank));
        return result;
    }

    private void addAllShape(List<Card> result, Rank rank) {
        Arrays.stream(Shape.values())
                .forEach((shape) -> result.add(new Card(shape, rank)));
    }

    public Card drawCard() {
        validateDeck();
        int pickCardIndex = RANDOM.nextInt(deck.size());
        return deck.remove(pickCardIndex);
    }

    private void validateDeck() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("카드가 없습니다.");
        }
    }

    public int size() {
        return deck.size();
    }
}
