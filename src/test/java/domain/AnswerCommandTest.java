package domain;

import controller.AnswerCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class AnswerCommandTest {

    @Test
    void 플레이어에게_카드를_더_받을지_묻는다() {
        //given
        final String command = "y";

        //when
        final AnswerCommand byAnswer = AnswerCommand.findByAnswer(command);

        //then
        assertThat(byAnswer).isNotNull()
                .isEqualTo(AnswerCommand.YES);
    }

    @Test
    void 플레이어가_유효하지_않은_커맨드를_입력하여_예외가_발생한다() {
        //given
        final String command = "d";

        //when
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> AnswerCommand.findByAnswer(command));
    }
}
