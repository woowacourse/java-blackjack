package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

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
}
