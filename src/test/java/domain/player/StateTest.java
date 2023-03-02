package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("State는")
class StateTest {

    @ParameterizedTest
    @CsvSource(value = {
            "HIT -> true",
            "STAY -> false"
    }, delimiterString = " -> ")
    @DisplayName("isHit() : Hit 일 경우에는 true, STAY 일 경우에는 false를 반환한다.")
    void test(State state, boolean result) throws Exception {

        //when & then
        assertEquals(state.isHit(), result);
    }
}
