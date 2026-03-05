package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드 덱을 생성한다.")
    void initCardDeck() {
        // when & then
        assertThatCode(CardDeck::init).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 한 장 드로우 한다.")
    void drawTest() {
        // given
        CardDeck cardDeck = CardDeck.init();

        // when
        Card draw = cardDeck.draw();

        // then
        assertThat(draw).isNotNull();
    }

    @Test
    @DisplayName("카드 덱에 더 이상 카드가 없는 경우, 드로우 시도하면 예외가 발생한다.")
    void drawExceptionTest() {
        // given
        CardDeck cardDeck = CardDeck.init();
        for (int i = 0; i < 52; i++) {
            cardDeck.draw();
        }

        // when & then
        assertThatThrownBy(cardDeck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("카드 덱에 카드가 더 이상 존재하지 않습니다.");
    }
}