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
}
