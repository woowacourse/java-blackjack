package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardTest {
    @DisplayName("카드의 숫자와 기호를 통해 카드를 생성한다.")
    @Test
    void Should_Create_When_NewCard() {
        assertThat(new Card(CardNumber.EIGHT, CardSymbol.HEARTS).getCardNumberToString()).isEqualTo("8");
    }

    @DisplayName("ACE 카드인지 확인한다.")
    @Nested
    class ace {
        @DisplayName("ACE 카드 이면 True을 리턴한다.")
        @Test
        void Should_True_When_isACE() {
            Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);

            assertThat(card.isACE()).isTrue();
        }

        @DisplayName("ACE 카드가 아니면 False를 리턴한다.")
        @Test
        void Should_False_When_isNotACE() {
            Card card = new Card(CardNumber.EIGHT, CardSymbol.HEARTS);

            assertThat(card.isACE()).isFalse();
        }
    }

}
