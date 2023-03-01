import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class NameTest {
    @DisplayName("이름을 생성한다.")
    @Test
    void 이름_생성() {
        assertDoesNotThrow(() -> new Name("name"));
    }

    @DisplayName("이름에 적절하지 않은 값이 입력되면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {" "})
    @NullAndEmptySource
    void 이름_적절하지_않은_값(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름에는 공백이나 빈 값이 들어갈 수 없습니다.");
    }
}
