package blackjack.domain.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @Test
    void 플레이어는_이름으로_생성한다() {
        // given
        final String name = "dompoo";

        // expected
        Assertions.assertDoesNotThrow(() -> new Player(name));
    }

    @Test
    void 플레이어를_생성하면_이름을_확인할_수_있다() {
        // given
        final Player player = new Player("dompoo");

        // when
        final String result = player.getName();

        // then
        assertThat(result).isEqualTo("dompoo");
    }

    @ParameterizedTest
    @ValueSource(strings = {"돔", "돔푸돔푸돔푸돔푸돔"})
    void 플레이어의_이름이_2에서_8글자가_아니면_예외를_발생시킨다(String name) {
        // expected
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 2자 이상, 8자 이하여야 합니다.");
    }

    @Test
    void 플레이어의_이름이_NULL인_경우_예외를_발생시킨다() {
        // given

        // expected
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new Player(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("필수 입력값 중 하나가 null입니다.");
    }
}
