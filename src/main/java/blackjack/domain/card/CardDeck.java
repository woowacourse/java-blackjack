package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<CardRank> cardRanks = CardRank.getAllCardRanks();
        List<CardSuit> cardSuits = CardSuit.getAllCardSuits();

        return cardRanks.stream()
                .flatMap(rank -> cardSuits.stream()
                        .map(suit -> new Card(rank, suit)))
                .collect(Collectors.toList());
    }

    public Card popCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 남아있는 카드가 부족하여 카드를 뽑을 수 없습니다.");
        }
        return cards.poll();
    }

    public List<Card> popCards(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> popCard())
                .toList();
    }
}
