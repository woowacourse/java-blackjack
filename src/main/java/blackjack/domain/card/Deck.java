package blackjack.domain.card;

import static blackjack.domain.BlackjackCardRule.INITIALLY_DISTRIBUTED_CARD_COUNT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.generator.DeckGenerator;

public class Deck {

    private final List<Card> cards;

    private Deck(final List<Card> cards) {
        validateCardNotDuplicated(cards);
        this.cards = cards;
    }

    public static Deck generate(final DeckGenerator deckGenerator) {
        return new Deck(deckGenerator.generate());
    }

    public List<Card> distributeInitialCards() {
        return drawCards(INITIALLY_DISTRIBUTED_CARD_COUNT.getCount());
    }

    private List<Card> drawCards(final int count) {
        final List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cards.add(drawCard());
        }
        return cards;
    }

    public Card drawCard() {
        validateDrawableCardExist();
        return cards.remove(0);
    }

    private static void validateCardNotDuplicated(final List<Card> cards) {
        if (isCardDuplicated(cards)) {
            throw new IllegalArgumentException("중복된 카드는 존재할 수 없습니다.");
        }
    }

    private static boolean isCardDuplicated(final List<Card> cards) {
        return cards.stream()
                .anyMatch(card -> Collections.frequency(cards, card) > 1);
    }

    private void validateDrawableCardExist() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 이상 뽑을 수 있는 카드가 없습니다.");
        }
    }

}
