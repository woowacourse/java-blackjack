package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import blackjack.domain.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandTest {

    @Test
    @DisplayName("yes 명령어를 조회한다.")
    void findYes() {
        String input = "y";

        assertThat(Command.find(input)).isEqualTo(Command.HIT);
    }

    @Test
    @DisplayName("no 명령어를 조회한다.")
    void findNo() {
        String input = "n";

        assertThat(Command.find(input)).isEqualTo(Command.STAY);
    }

    @Test
    @DisplayName("존재하지 않는 명령일 경우 예외를 발생한다.")
    void notFindCommand() {
        String input = "noop";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Command.find(input))
                .withMessageContaining("존재하지 않는 명령입니다.");
    }

    @Test
    @DisplayName("자신이 HIT일 경우 True 반환한다.")
    void isHit() {
        assertThat(Command.HIT.isHit()).isTrue();
    }

    @Test
    @DisplayName("자신이 STAY일 경우 True 반환한다.")
    void isStay() {
        assertThat(Command.STAY.isStay()).isTrue();
    }
}
