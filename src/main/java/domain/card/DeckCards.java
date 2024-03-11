package domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
import strategy.CardGenerator;

public class DeckCards {

    private final Queue<Card> cards;

    private DeckCards(Queue<Card> cards) {
        validateNullOrEmpty(cards);
        this.cards = cards;
    }

    public static DeckCards from(CardGenerator cardGenerator) {
        Queue<Card> generatedCards = new LinkedList<>(cardGenerator.generate());
        return new DeckCards(generatedCards);
    }

    public List<Card> drawStartingCards(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("[ERROR] 카드는 1장 이상 뽑아야 합니다.");
        }
        return IntStream.range(0, amount)
            .mapToObj(i -> draw())
            .toList();
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드를 모두 사용하였습니다.");
        }
        return cards.poll();
    }

    private void validateNullOrEmpty(Queue<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("[ERROR] 덱은 null일 수 없습니다.");
        }
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 덱은 비어 있을 수 없습니다.");
        }
    }
}
