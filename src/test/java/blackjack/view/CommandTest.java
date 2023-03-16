package blackjack.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    void y_n_은_대_소문자를_구분하지않고_같다고_본다() {
        // given
        Command smallCase = Command.from("y");
        Command bigCase = Command.from("Y");

        // then
        Assertions.assertThat(smallCase).isEqualTo(bigCase);
    }
}
