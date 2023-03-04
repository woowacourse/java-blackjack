package domain.card;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CardTest {

    @ParameterizedTest(name = "create()는 전달받은 CardPattern과 CardNumber를 조합하여 카드를 생성한다")
    @CsvSource(value = {"HEART:ACE", "SPADE:TWO", "DIAMOND:THREE", "CLOVER:FOUR"}, delimiter = ':')
    void create_givenCardPatternAndCardNumber_thenSuccess(final CardPattern cardPattern, final CardNumber cardNumber) {
        final Card card = assertDoesNotThrow(() -> Card.create(cardPattern, cardNumber));

        assertThat(card)
                .isExactlyInstanceOf(Card.class);
    }

    @ParameterizedTest(name = "checkAce()는 호출하면, 에이스인지 여부를 반환한다")
    @CsvSource(value = {"ACE:true", "TWO:false", "KING:false"}, delimiter = ':')
    void checkAce_whenCall_thenReturnIsAce(final CardNumber cardNumber, final boolean expected) {
        // given
        final Card card = Card.create(CardPattern.HEART, cardNumber);

        // when
        final boolean actual = card.checkAce();

        assertThat(actual)
                .isSameAs(expected);
    }
}
