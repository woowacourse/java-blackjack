package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = createShuffledDeck();
    }

    private static List<Card> createShuffledDeck() {
        List<Card> deck = createDeck();
        return shuffle(deck);
    }

    private static List<Card> createDeck() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(rank, suit)))
                .collect(Collectors.toList());
    }

    private static List<Card> shuffle(List<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }

    public Card drawCard() {
        return cards.removeFirst();
    }

    public List<Card> drawCards(int count) {
        validateEnoughCard(count);

        List<Card> drawnCards = new ArrayList<>(cards.subList(0, count));
        cards.subList(0, count).clear();

        return List.copyOf(drawnCards);
    }

    private void validateEnoughCard(int count) {
        if (cards.size() < count) {
            throw new IllegalArgumentException("남은 카드가 뽑을 카드보다 적습니다.");
        }
    }
}
