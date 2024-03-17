package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardDeck {

    private Queue<Card> cards;

    CardDeck(Queue<Card> cards) {
        this.cards = cards;
    }

    CardDeck(List<Card> cards) {
        this((Queue<Card>) new LinkedList<>(cards));
    }

    public static CardDeck createShuffledDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(shape, number)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    public Card popCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 남아있는 카드가 부족하여 카드를 뽑을 수 없습니다");
        }
        return cards.poll();
    }

    public List<Card> popCards(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> popCard())
                .collect(Collectors.toList());
    }
}
