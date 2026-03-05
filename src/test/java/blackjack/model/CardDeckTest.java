package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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
}