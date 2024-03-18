package domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
import strategy.CardGenerator;

public class Deck {

    private static final int CARD_AMOUNT = 52;

    private final Queue<Card> cards;

    private Deck(Queue<Card> cards) {
        validate(cards);
        this.cards = cards;
    }

    public static Deck generatedBy(CardGenerator cardGenerator) {
        Queue<Card> generatedCards = new LinkedList<>(cardGenerator.generate());
        return new Deck(generatedCards);
    }

    private void validate(Queue<Card> cards) {
        validateNull(cards);
        validateAmount(cards);
    }

    private void validateNull(Queue<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("[ERROR] 덱은 null일 수 없습니다.");
        }
    }

    private void validateAmount(Queue<Card> cards) {
        if (cards.size() != CARD_AMOUNT) {
            throw new IllegalArgumentException(String.format("[ERROR] 덱에는 %d장의 카드가 있어야 합니다.", CARD_AMOUNT));
        }
    }


    public List<Card> drawCards(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("[ERROR] 카드는 1장 이상 뽑아야 합니다.");
        }
        return IntStream.range(0, amount)
            .mapToObj(i -> drawCard())
            .toList();
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드를 모두 사용하였습니다.");
        }
        return cards.poll();
    }
}
