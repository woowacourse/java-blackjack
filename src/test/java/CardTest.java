import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CardTest {

    @DisplayName("2 ~ 10 범위에 해당하지 않는 값의 카드는 생성할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "11", "12", "100"})
    void 생성자_숫자_검증_테스트(String value) {
        // when & then
        assertThatThrownBy(() -> {
            new Card(value, "spade");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자 리스트에 포함되는 값으로 카드를 생성한다")
    @ParameterizedTest
    @ValueSource(strings = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Q", "K", "J"})
    void 카드_생성_테스트(String value) {
        // when & then
        assertThatCode(() -> {
            new Card(value, "spade");
        }).doesNotThrowAnyException();
    }
}
