package domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import expcetion.BlackjackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("이름이 숫자면 예외가 발생한다.")
    void 이름이_숫자일_시() {
        // given
        String name = "12345";

        // when & then
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("이름은 두글자 이상 열글자 미만이어야 한다.")
    void 이름_길이_검증() {
        // given
        String longName = "tobiisverygood";
        String shortName = "h";

        // when & then
        assertThatThrownBy(() -> new Name(longName))
                .isInstanceOf(BlackjackException.class);

        assertThatThrownBy(() -> new Name(shortName))
                .isInstanceOf(BlackjackException.class);
    }
}