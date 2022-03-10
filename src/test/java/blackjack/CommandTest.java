package blackjack;

import blackjack.domain.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CommandTest {

    @Test
    @DisplayName("yes 명령어를 조회한다.")
    void findYes() {
        String input = "y";

        assertThat(Command.find(input)).isEqualTo(Command.YES);
    }

    @Test
    @DisplayName("no 명령어를 조회한다.")
    void findNo() {
        String input = "n";

        assertThat(Command.find(input)).isEqualTo(Command.NO);
    }

    @Test
    @DisplayName("존재하지 않는 명령일 경우 예외를 발생한다.")
    void notFindCommand() {
        String input = "noop";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Command.find(input))
                .withMessageContaining("존재하지 않는 명령입니다.");
    }
}
