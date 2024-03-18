package domain.card;


import static domain.card.TestCards.ACE_HEART;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private static final FirstCardSelectStrategy FIRST_CARD_SELECT_STRATEGY = new FirstCardSelectStrategy();

    @Test
    @DisplayName("원하는 방식대로 카드가 뽑히는지 검증")
    void validateDraw() {
        Deck deck = Deck.of(ACE_HEART);
        Card card = deck.draw(FIRST_CARD_SELECT_STRATEGY);
        Assertions.assertThat(card)
                .isEqualTo(ACE_HEART);
    }

    @Test
    @DisplayName("중복된 카드가 포함된 덱이 생성되지 않는지 검증")
    void validateDuplicateCard() {
        Assertions.assertThatThrownBy(() -> Deck.of(ACE_HEART, ACE_HEART))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되는 카드가 있습니다.");
    }

    @Test
    @DisplayName("한 번 뽑힌 카드가 또 뽑히지 않는지 검증")
    void validateDrawedCardIsRemoved() {
        Deck deck = Deck.of(ACE_HEART);
        deck.draw(cards -> cards.get(0));

        Assertions.assertThatThrownBy(() -> deck.draw(FIRST_CARD_SELECT_STRATEGY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱이 비어있습니다.");
    }
}
