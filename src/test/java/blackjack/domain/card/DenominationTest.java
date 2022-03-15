package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.element.Denomination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DenominationTest {
    
    @ParameterizedTest
    @CsvSource(value = {
            "A,ACE"
            , "2,TWO"
            , "3,THREE"
            , "4,FOUR"
            , "5,FIVE"
            , "6,SIX"
            , "7,SEVEN"
            , "8,EIGHT"
            , "9,NINE"
            , "10,TEN"
            , "J,JACK"
            , "Q,QUEEN"
            , "K,KING"
    })
    @DisplayName("숫자 알맞게 들어가는지 검사")
    void setExpectedDenominationTest(String numStr, Denomination expectedDenomination) {
        Denomination denomination = Denomination.stringOf(numStr);
        assertThat(denomination).isEqualTo(expectedDenomination);
    }
}
