package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.element.Denomination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DenominationTest {

    @ParameterizedTest
    @CsvSource(value = {
            "A,ACE,11"
            , "2,TWO,2"
            , "3,THREE,3"
            , "4,FOUR,4"
            , "5,FIVE,5"
            , "6,SIX,6"
            , "7,SEVEN,7"
            , "8,EIGHT,8"
            , "9,NINE,9"
            , "10,TEN,10"
            , "J,JACK,10"
            , "Q,QUEEN,10"
            , "K,KING,10"
    })

    @DisplayName("숫자 알맞게 들어가는지 테스트")
    void setExpectedDenominationTest(String numStr, Denomination expectedDenomination, int expectedPoint) {
        Denomination denomination = Denomination.from(numStr);
        assertThat(denomination)
                .isEqualTo(expectedDenomination);
        assertThat(denomination.getPoint())
                .isEqualTo(expectedPoint);
    }
}
