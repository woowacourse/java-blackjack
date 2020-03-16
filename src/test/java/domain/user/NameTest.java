package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class NameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("플레이어 이름 null,empty 예외처리")
    void nullAndEmptyTest(String input) {
        assertThatThrownBy(() -> new Name(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름이 비어있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"'오렌지','true'", "'히히','false'"})
    @DisplayName("이름 같은지 확인")
    void isSameTest(String name, boolean result) {
        Name playerName = new Name("오렌지");
        assertThat(playerName.isSame(name)).isEqualTo(result);
    }
}
