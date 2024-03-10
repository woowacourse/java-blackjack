package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import strategy.CardGenerator;

public class DeckCards {

    private final List<Card> cards;

    private DeckCards(List<Card> cards) {
        this.cards = cards;
    }

    public static DeckCards from(CardGenerator cardGenerator) {
        List<Card> generatedCards = new ArrayList<>(cardGenerator.generate());
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
        return cards.remove(cards.size() - 1);
    }
}
