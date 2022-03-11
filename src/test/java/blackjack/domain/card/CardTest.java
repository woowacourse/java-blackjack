package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @Test
    @DisplayName("카드를 생성한다")
    void create() {
        assertDoesNotThrow(() -> new Card(Denomination.TWO, Suit.SPADE));
    }

    @Test
    @DisplayName("카드를 생설할때 null 일 경우 예외 발생")
    void throwExceptionByNull() {
        assertThatThrownBy(() -> new Card(null, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("null");
    }

    @ParameterizedTest
    @MethodSource("equalsCardTestCase")
    @DisplayName("카드가 정상적인 무늬와 끗수의 값이 정상적으로 생성되었는지 확인")
    void equalsCardSuccess(Denomination denomination, Suit suit) {
        Card card1 = new Card(denomination, suit);
        Card card2 = new Card(denomination, suit);
        Card card3 = new Card(Denomination.TWO, Suit.CLOVER);

        assertAll(
            () -> assertThat(card1).isEqualTo(card2),
            () -> assertThat(card1).hasSameHashCodeAs(card2),
            () -> assertThat(card1).isNotEqualTo(card3)
        );

    }

    private static Stream<Arguments> equalsCardTestCase() {
        return Stream.of(
            Arguments.of(Denomination.ACE, Suit.CLOVER),
            Arguments.of(Denomination.KING, Suit.DIAMOND),
            Arguments.of(Denomination.ACE, Suit.HEART),
            Arguments.of(Denomination.KING, Suit.SPADE)
        );
    }

    @Test
    @DisplayName("카드의 속성 중 어느하나라도 같지 않으면 동등성 또한 같지 않다")
    void doesNotEqualsCard() {
        Card card1 = new Card(Denomination.TWO, Suit.SPADE);
        Card card2 = new Card(Denomination.TWO, Suit.CLOVER);
        Card card3 = new Card(Denomination.THREE, Suit.CLOVER);

        assertAll(
            () -> assertThat(card1).isNotEqualTo(card2),
            () -> assertThat(card2).isNotEqualTo(card3),
            () -> assertThat(card3).isNotEqualTo(card2)
        );
    }

    @Test
    @DisplayName("카드가 정상적으로 생성되었을때 그 값이 정학환지 확인")
    void createCardEqualsValue() {
        Card card = new Card(Denomination.TEN, Suit.SPADE);

        assertAll(
            () -> assertThat(card.getDenomination()).isEqualTo("10"),
            () -> assertThat(card.getSuit()).isEqualTo("스페이드")
        );
    }
}
