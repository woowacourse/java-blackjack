package utils;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import view.mesage.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InputParserTest {

    @Nested
    @DisplayName("사용자 입력 값 구분자(',') 분리 검증")
    class SplitByDelimiter {

        @Test
        @DisplayName("사용자 이름 입력 값이 구분자를 통해 정상 분리")
        void 사용자_이름_입력값_분리_정상_테스트() {
            String input = "pobi,jason";

            List<String> usernames = InputParser.splitByDelimiter(input);

            List<String> expect = List.of("pobi", "jason");
            assertThat(usernames).isEqualTo(expect);
        }

        @Test
        @DisplayName("사용자 이름 입력 값이 공백인 경우 구분자 분리 실패")
        void 사용자_빈_이름_입력값_실패_테스트() {
            String input = "";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputParser.splitByDelimiter(input))
                    .withMessage(ErrorMessage.EMPTY_NAME_INPUT.getMessage());
        }

        @Test
        @DisplayName("유효한 사용자 0명 입력했을 시 에러 발생")
        void 유효한_사용자_0명_입력_실패_테스트() {
            String input = "   , ,";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputParser.splitByDelimiter(input))
                    .withMessage(ErrorMessage.INVALID_NAMES_EMPTY.getMessage());
        }

        @Test
        @DisplayName("사용자 25명 초과 입력했을 시 에러 발생")
        void 사용자_26명_입력_실패_테스트() {
            String input = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputParser.splitByDelimiter(input))
                    .withMessage(ErrorMessage.INVALID_NAMES_EXCEED_LIMIT.getMessage());
        }
    }
}

