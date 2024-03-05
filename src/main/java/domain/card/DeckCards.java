package domain.card;

import java.util.ArrayList;
import java.util.List;
import strategy.CardGenerator;

public class DeckCards {

    private static final int INITIAL_CARD_AMOUNT = 52;

    private final List<Card> cards;

    private DeckCards(List<Card> cards) {
        if (cards.size() != INITIAL_CARD_AMOUNT) {
            throw new IllegalStateException(
                String.format("[ERROR] 덱에는 %d장의 카드가 있어야 합니다.", INITIAL_CARD_AMOUNT));
        }
        this.cards = cards;
    }

    public static DeckCards from(CardGenerator cardGenerator) {
        List<Card> generatedCards = new ArrayList<>();
        while (cardGenerator.hasNext()) {
            generatedCards.add(cardGenerator.nextCard());
        }
        return new DeckCards(generatedCards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드를 모두 사용하였습니다.");
        }
        return cards.remove(cards.size() - 1);
    }
}
