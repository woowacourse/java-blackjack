package blackjack.domain.card;

import blackjack.domain.card.generator.CardDeckGenerator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Queue<Card> cardDeck;

    public CardDeck(CardDeckGenerator cardDeckGenerator) {
        this.cardDeck = new LinkedList<>(cardDeckGenerator.generate());
    }

    public Cards drawInitialCards() {
        List<Card> initialCards = new ArrayList<>(INITIAL_CARD_COUNT);
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            initialCards.add(draw());
        }
        return new Cards(initialCards);
    }

    public Card draw() {
        checkEmptyCardDeck();
        return cardDeck.poll();
    }

    private void checkEmptyCardDeck() {
        if (cardDeck.isEmpty()) {
            throw new IllegalStateException("카드덱에 남은 카드가 없습니다.");
        }
    }
}
