package blackjack.domain.deck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeckTest {

    @Test
    @DisplayName("카드 덱은 총 52장으로 이루어져야한다.")
    void deckSizeTest() {
        // given
        Deck deck = new Deck();

        // when & then
        assertThat(deck.getDeckSize()).isEqualTo(52);
    }

    @Test
    @DisplayName("덱에서 카드 한 장을 분배하고 삭제한다.")
    void hitTest() {
        // given
        Deck deck = new Deck();

        // when
        deck.hit();

        // then
        assertThat(deck.getDeckSize()).isEqualTo(51);
    }
}
