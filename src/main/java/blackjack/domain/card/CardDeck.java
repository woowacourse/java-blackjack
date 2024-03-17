package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private final Queue<Card> cards;

    public CardDeck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static CardDeck createShuffledFullCardDeck() {
        List<Card> cards = createFullCardDeck();
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private static List<Card> createFullCardDeck() {
        List<CardRank> cardRanks = Arrays.asList(CardRank.values());
        List<CardSuit> cardSuits = Arrays.asList(CardSuit.values());

        return cardRanks.stream()
                .flatMap(rank -> generateCards(rank, cardSuits))
                .collect(Collectors.toList());
    }

    private static Stream<Card> generateCards(CardRank rank, List<CardSuit> cardSuits) {
        return cardSuits.stream()
                .map(suit -> new Card(rank, suit));
    }

    public Card popCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 남아있는 카드가 부족하여 카드를 뽑을 수 없습니다.");
        }
        return cards.poll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardDeck other = (CardDeck) o;
        return cards.containsAll(other.cards) && other.cards.containsAll(this.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
