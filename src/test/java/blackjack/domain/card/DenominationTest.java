package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Denomination 생성 테스트")
    void createDenomination(String numStr, Denomination expectedDenomination) {
        Denomination denomination = Denomination.of(numStr);
        assertThat(denomination)
            .isEqualTo(expectedDenomination);
    }

    @Test
    @DisplayName("Denomination 생성 실패 테스트")
    void CreateDenominationExceptionTest() {
        assertThatThrownBy(() -> Denomination.of("B"))
            .hasMessage("[ERROR] 존재하지 않는 끗수입니다.");
    }
}
