package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class InputViewTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Test
    void 빈_카드_수령_여부_예외_테스트() {
        // given
        String answer = "";

        // when & then
        assertThatThrownBy(() ->
                InputView.validateGetMoreEmptyInput(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    void 참가자_수_초과_예외_테스트() {
        // given
        List<String> names = List.of("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q");

        // when & then
        assertThatThrownBy(() -> InputView.validateParticipantsNumbers(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    void y_n_외의_문자_입력_테스트() {
        // given
        String answer = "d";

        // when & then
        assertThatThrownBy(() ->
                InputView.validateYesOrNo(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

}
