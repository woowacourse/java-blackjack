package blackjack;

import blackjack.domain.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeTest {
    @DisplayName("타입별 점수 확인")
    @ParameterizedTest
    @CsvSource({"ACE,1", "TWO,2", "THREE,3", "FOUR,4", "FIVE,5", "SIX,6", "SEVEN,7", "EIGHT,8", "NINE,9", "TEN,10",
            "JACK,10", "QUEEN,10", "KING,10"})
    void getPointByTypeTest(Type type, int expected) {
        int point = type.getPoint();
        assertThat(point).isEqualTo(expected);
    }
}
