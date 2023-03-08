package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewCard() {
        assertDoesNotThrow(() -> new Card(CardNumber.EIGHT, CardSymbol.HEARTS));
    }

    @DisplayName("ACE 카드 확인 테스트")
    @Nested
    class ace {
        @DisplayName("ACE 카드 일 때")
        @Test
        void Should_True_When_isACE() {
            Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);

            assertThat(card.isACE()).isTrue();
        }

        @DisplayName("ACE 카드가 아닐 때")
        @Test
        void Should_False_When_isNotACE() {
            Card card = new Card(CardNumber.EIGHT, CardSymbol.HEARTS);

            assertThat(card.isACE()).isFalse();
        }
    }

}
