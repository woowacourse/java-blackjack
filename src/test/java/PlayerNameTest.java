import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerNameTest {

    @DisplayName("이름의 길이가 1글자 미만이거나 20글자를 초과하면 생성에 실패한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "123456789012345678901"})
    void createNameByInvalidLengthTest(String name) {
        assertThatThrownBy(() -> new PlayerName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름의 길이는 1 ~ 20 글자 사이로 입력해주세요.");
    }

    @DisplayName("이름의 길이가 1글자 이상이거나 20글자 이하면 생성에 성공한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "12345678901234567890"})
    void createNameByValidLengthTest(String name) {
        assertThatCode(() -> new PlayerName(name))
                .doesNotThrowAnyException();
    }
}
