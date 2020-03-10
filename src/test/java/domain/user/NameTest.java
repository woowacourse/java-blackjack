package domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class NameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("플레이어 이름 null,empty 예외처리")
    void nullAndEmptyTest(String input) {
        assertThatThrownBy(() -> new Name(input)).
            isInstanceOf(NullPointerException.class);
    }

}
