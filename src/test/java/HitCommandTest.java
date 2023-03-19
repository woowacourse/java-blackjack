import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import controller.HitCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("HitCommand 는")
class HitCommandTest {

    @Test
    void y_또는_n_만_사용할_수_있다() {
        // given

        // when

        // then
        assertThat(HitCommand.find("y")).isEqualTo(HitCommand.HIT);
        assertThat(HitCommand.find("n")).isEqualTo(HitCommand.STAY);
    }

    @Test
    void y_또는_n_을_사용하지_않으면_에러가_발생한다() {
        // given

        // when

        // then
        assertThatThrownBy(() -> HitCommand.find("배럴쵝오"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] y 혹은 n만 입력하실 수 있습니다.");
    }
}
