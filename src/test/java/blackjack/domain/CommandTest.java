package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {
    
    @DisplayName("입력한 값에 따라 Command가 정상적으로 생성된다.")
    @Test
    void createCommand() {
        // given
        Command hitCommand = Command.from("y");
        Command stayCommand = Command.from("n");

        // when & then
        assertThat(hitCommand).isEqualTo(Command.HIT);
        assertThat(stayCommand).isEqualTo(Command.STAY);
    }

    @DisplayName("잘못된 입력값이 들어온 경우 예외가 발생한다.")
    @Test
    void checkValidateCommand() {
        // given
        String input = "wrong";

        // when & then
        assertThatThrownBy(() -> Command.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n만 입력 가능합니다.");
    }
}
