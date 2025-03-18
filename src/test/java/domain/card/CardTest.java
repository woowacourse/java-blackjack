package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource({
            "A, true", "TWO, false", "THREE, false", "FOUR, false", "FIVE, false",
            "SIX, false", "SEVEN, false", "EIGHT, false", "NINE, false", "TEN, false",
            "QUEEN, false", "KING, false", "JACK, false"
    })
    void 카드의_숫자가_에이스인지_아닌지_반환한다(CardNumber cardNumber, boolean expected) {
        //given
        Card card = new Card(cardNumber, CardShape.CLOVER);
        //when
        boolean actual = card.isAce();
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
