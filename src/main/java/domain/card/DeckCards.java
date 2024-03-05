package domain.card;

import java.util.ArrayList;
import java.util.List;
import strategy.CardGenerator;

public class DeckCards {

    private final List<Card> cards;

    private DeckCards(List<Card> cards) {
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
