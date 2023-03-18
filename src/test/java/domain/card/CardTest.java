package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {

    @Nested
    @DisplayName("of() 테스트")
    class OfMethodTest {

        @ParameterizedTest(name = "전달받은 CardPattern과 CardNumber를 조합하여 캐싱된 카드를 가져온다")
        @CsvSource(value = {"HEART:ACE", "SPADE:TWO", "DIAMOND:THREE", "CLOVER:FOUR"}, delimiter = ':')
        void of_givenCardPatternAndCardNumber_thenSuccess(final Shape shape, final Denomination denomination) {
            final Card card = assertDoesNotThrow(() -> Card.of(shape, denomination));

            assertThat(card)
                    .isExactlyInstanceOf(Card.class);
        }

        @ParameterizedTest(name = "캐싱된 카드를 가져오는 경우 카드는 항상 동일하다")
        @CsvSource(value = {"HEART:ACE", "SPADE:TWO", "DIAMOND:THREE", "CLOVER:FOUR"}, delimiter = ':')
        void of_givenCardPatternAndCardNumber_thenAlways(final Shape shape, final Denomination denomination) {
            final Card firstCard = Card.of(shape, denomination);
            final Card secondCard = Card.of(shape, denomination);

            assertThat(firstCard)
                    .isSameAs(secondCard);

            assertThat(firstCard)
                    .isEqualTo(secondCard);
        }
    }

    @ParameterizedTest(name = "checkAce()는 호출하면 에이스인지 여부를 반환한다")
    @CsvSource(value = {"ACE:true", "TWO:false", "KING:false"}, delimiter = ':')
    void checkAce_whenCall_thenReturnIsAce(final Denomination denomination, final boolean expected) {
        // given
        final Card card = Card.of(Shape.HEART, denomination);

        // when
        final boolean actual = card.checkAce();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
