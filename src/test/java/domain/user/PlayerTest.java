package domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PlayerTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Player 생성 시 이름 인자의 null, empty 체크")
    void nullAndEmptyTest(String input) {
        assertThatThrownBy(() -> new Player(input)).
            isInstanceOf(NullPointerException.class);
    }

}
