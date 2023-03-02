package domain.card;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CardTest {

    @ParameterizedTest(name = "create()는 전달받은 CardPattern과 CardNumber를 조합하여 카드를 생성한다")
    @CsvSource(value = {"HEART:ACE", "SPADE:TWO", "DIAMOND:THREE", "CLOVER:FOUR"}, delimiter = ':')
    void create_givenCardPatternAndCardNumber_thenSuccess(final CardPattern cardPattern, final CardNumber cardNumber) {
        assertThatCode(() -> Card.create(cardPattern, cardNumber))
                .doesNotThrowAnyException();

        assertThat(Card.create(cardPattern, cardNumber))
                .isExactlyInstanceOf(Card.class);
    }

    @ParameterizedTest(name = "isAce()는 호출하면, 에이스인지 여부를 반환한다")
    @CsvSource(value = {"ACE:true", "TWO:false", "KING:false"}, delimiter = ':')
    void isAce_whenCall_thenReturnIsAce(final CardNumber cardNumber, final boolean expected) {
        // given
        Card card = Card.create(CardPattern.HEART, cardNumber);

        // when
        boolean actual = card.isAce();

        AssertionsForClassTypes.assertThat(actual).isSameAs(expected);
    }
}
