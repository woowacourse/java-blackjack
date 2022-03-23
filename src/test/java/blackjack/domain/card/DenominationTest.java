package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.card.denomination.Denomination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DenominationTest {

    @ParameterizedTest
    @CsvSource(value = {
            "ACE,11"
            , "TWO,2"
            , "THREE,3"
            , "FOUR,4"
            , "FIVE,5"
            , "SIX,6"
            , "SEVEN,7"
            , "EIGHT,8"
            , "NINE,9"
            , "TEN,10"
            , "JACK,10"
            , "QUEEN,10"
            , "KING,10"
    })

    @DisplayName("숫자 알맞게 들어가는지 테스트")
    void setExpectedDenominationTest(Denomination expectedDenomination, int expectedPoint) {
        assertThat(expectedDenomination.getPoint())
                .isEqualTo(expectedPoint);
    }
}
