package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @ParameterizedTest(name = "이름이 공백이거나 빈칸일 경우")
    @NullAndEmptySource
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void validateName(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[Error] 이름은 공백이거나 빈칸일 수 없습니다.");
    }
}
