package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class DenominationTest {

    @ParameterizedTest
    @DisplayName("카드 점수 판정 테스트")
    @CsvSource({"2,2", "3,3", "4,4", "5,5", "6,6", "7,7", "8,8", "9,9", "10,10", "J,10", "Q,10", "K,10"})
    void 정상_테스트_1(String symbol, String expected) {
        Denomination denomination = Denomination.pick(symbol);

        assertEquals(denomination.toScore(), Integer.parseInt(expected));
    }


}
