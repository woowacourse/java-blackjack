package blackjack.domain;

import java.util.List;
import java.util.stream.IntStream;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public Card popCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 남아있는 카드가 부족하여 카드를 뽑을 수 없습니다");
        }
        return cards.remove(0);
    }

    public List<Card> popCards(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> popCard())
                .toList();
    }
}
