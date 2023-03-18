package blackjack.domain.player;

import blackjack.domain.user.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class NameTest {

    @DisplayName("입력한 이름의 길이가 1 이상 10 이하가 아니라면 예외처리 한다.")
    @Test
    void validate_name_length() {
        // given
        String name = "aaaaaaaaaaa";

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessageContaining("이름의 길이는 1 이상 10 이하로 입력해주세요.");
    }

    @DisplayName("입력한 이름의 길이가 1 이상 10 이하라면 Name 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "fivee", "lengthTenn", "딜러"})
    void generate_name(String name) {
        // when & then
        assertThatNoException()
                .isThrownBy(() -> new Name(name));
    }

}