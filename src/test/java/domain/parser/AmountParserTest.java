package domain.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import expcetion.BlackjackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parser.AmountParser;

public class AmountParserTest {
    @Test
    @DisplayName("인풋 정상 입력")
    void 인풋_정상_입력(){
        //given
        String input = "123464";
        //when
        int num = AmountParser.parse(input);
        //then
        assertThat(num).isEqualTo(123464);
    }

    @Test
    @DisplayName("인풋 숫자 파싱 예외")
    void 인풋_숫자_파싱_예외(){
        //given
        String input = "12a";
        //when & then
        assertThatThrownBy(()-> AmountParser.parse(input))
                .isInstanceOf(BlackjackException.class);
    }
}
