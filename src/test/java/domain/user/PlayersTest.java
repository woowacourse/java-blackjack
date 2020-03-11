package domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PlayersTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력된 이름 리스트의 null, empty 예외처리")
    void nullAndEmptyTest(List<String> input) {
        assertThatThrownBy(() -> new Players(input)).
            isInstanceOf(NullPointerException.class);
    }

}
