package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
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
    void 테스트(String numStr, Denomination expectedDenomination) {
        Denomination denomination = Denomination.of(numStr);
        assertThat(denomination)
                .isEqualTo(expectedDenomination);
    }
}