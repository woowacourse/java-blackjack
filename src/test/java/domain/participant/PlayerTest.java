package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class PlayerTest {

    @Test
    @DisplayName("create()를 호출하면, Player가 생성된다")
    void create_whenCall_thenSuccess() {
        // given
        final String name = "pobi";

        // when, then
        assertThatCode(() -> Player.create(name))
                .doesNotThrowAnyException();

        assertThat(Player.create(name))
                .isExactlyInstanceOf(Player.class);
    }

    @Test
    @DisplayName("isDealerName()은 파라미터로 입력된 name이 '딜러'인지 판단한다")
    void create_givenInvalidName_thenFail() {
        assertThatThrownBy(() -> Player.create("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
    }
}
