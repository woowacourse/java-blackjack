package blackjackgame.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    private static final int DECK_SIZE = 52;

    @Test
    @DisplayName("중복된 카드가 포함된 덱이 생성되지 않는지 확인한다.")
    void validateDuplicateCardTest() {
        Assertions.assertThatCode(Deck::fullDeck)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드가 잘 드로우되는지 확인한다.")
    void drawTest() {
        Deck deck = Deck.fullDeck();
        Card card = deck.draw(cards -> cards.get(0));
        Assertions.assertThat(card).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("덱에 카드가 없으면 뽑지 못하는지 확인한다.")
    void validateNoCardTest() {
        Deck deck = Deck.fullDeck();
        for (int i = 0; i < DECK_SIZE; i++) {
            deck.draw(cards -> cards.get(0));
        }

        Assertions.assertThatThrownBy(() -> deck.draw(cards -> cards.get(0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱이 비어있습니다.");
    }
}
