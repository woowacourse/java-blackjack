package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

class AddCardCommandTest {
    
    @Test
    @DisplayName("커맨드에 매핑되는 데이터 가져오기")
    void valueOfCommand() {
        assertAll(
                () -> assertThat(AddCardCommand.valueOfCommand("y")).isEqualTo(AddCardCommand.YES),
                () -> assertThat(AddCardCommand.valueOfCommand("n")).isEqualTo(AddCardCommand.NO),
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> AddCardCommand.valueOfCommand(" "))
                        .withMessage("카드를 더 받는 여부의 입력은 y 또는 n 만 입력할 수 있습니다.")
        );
    }
    
    @Test
    @DisplayName("카드를 더 받겠다고 선택한 것인지 확인")
    void isAddCardCommand() {
        assertAll(
                () -> assertThat(AddCardCommand.YES.isAddCardCommand()).isTrue(),
                () -> assertThat(AddCardCommand.NO.isAddCardCommand()).isFalse()
        );
    }
    
    @Test
    @DisplayName("카드를 더 받지 않겠다고 선택한 것인지 확인")
    void isNotAddCardCommand() {
        assertAll(
                () -> assertThat(AddCardCommand.YES.isNotAddCardCommand()).isFalse(),
                () -> assertThat(AddCardCommand.NO.isNotAddCardCommand()).isTrue()
        );
    }
}