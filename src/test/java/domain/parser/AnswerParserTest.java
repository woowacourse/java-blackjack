package domain.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import expcetion.BlackjackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parser.AnswerParser;

public class AnswerParserTest {
    @Test
    @DisplayName("사용자의 응답이 정상 작동")
    void 사용자_응답_정상() {
        //given & when
        boolean answerTrue = AnswerParser.parse("y");
        boolean answerFalse = AnswerParser.parse("n");
        //then
        assertThat(answerTrue).isTrue();
        assertThat(answerFalse).isFalse();
    }

    @Test
    @DisplayName("사용자의 응답 예외")
    void 사용자_응답_예외() {
        //given & when & then
        assertThatThrownBy(() -> AnswerParser.parse(".."))
                .isInstanceOf(BlackjackException.class);
    }
}
