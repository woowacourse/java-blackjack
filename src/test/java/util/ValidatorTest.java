package util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {
    public static final String ERROR_PREFIX = "[ERROR] ";
    Validator validator = new Validator();

    @Test
    @DisplayName("참가자 이름이 비어있지 않은 경우, 정상 동작한다.")
    void 빈_참가자_이름_정상_테스트() {
        // given
        String participantsName = "라이";

        // when & then
        assertThatCode(() -> validator.validatePlayersName(participantsName))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자 이름이 비어있는 경우, IllegalArgumentException이 발생한다.")
    void 빈_참가자_이름_예외_테스트() {
        // given
        String participantsName = "";

        // when & then
        assertThatThrownBy(() ->
                validator.validatePlayersName(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    @DisplayName("참가자 이름에 숫자가 포함되어 있지 않은 경우, 정상 동작한다.")
    void 참가자_이름_숫자_정상_테스트() {
        // given
        String participantsName = "라이";

        // when & then
        assertThatCode(() -> validator.validatePlayersName(participantsName))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자 이름에 숫자가 포함되는 경우, IllegalArgumentException이 발생한다.")
    void 참가자_이름_숫자_예외_테스트() {
        // given
        String participantsName = "123";

        // when & then
        assertThatThrownBy(() ->
                validator.validatePlayersName(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    @DisplayName("참가자 이름을 쉼표로 구분하여 입력하는 경우, 정상 동작한다.")
    void 참가자_이름_쉼표_정상_테스트() {
        // given
        String participantsName = "라이,영기";

        // when & then
        assertThatCode(() -> validator.validatePlayersName(participantsName))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자 이름에 쉼표를 제외한 특수문자가 포함되는 경우, IllegalArgumentException이 발생한다.")
    void 쉼표_외의_특수문자_예외_테스트() {
        // given
        String participantsName = "영기:라이";

        // when & then
        assertThatThrownBy(() ->
                validator.validatePlayersName(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    @DisplayName("카드 수령 여부(y/n)가 비어있지 않은 경우, 정상 동작한다.")
    void 빈_카드_수령_여부_정상_테스트() {
        // given
        String answer = "y";

        //when & then
        assertThatCode(() -> validator.validateAnswer(answer))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 수령 여부(y/n)가 비어있는 경우, IllegalArgumentException이 발생한다.")
    void 빈_카드_수령_여부_예외_테스트() {
        // given
        String answer = "";

        // when & then
        assertThatThrownBy(() ->
                validator.validateAnswer(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(strings = {"y", "Y", "n", "N"})
    @DisplayName("대소문자 상관없이 y/n을 입력하는 경우, 정상 동작한다.")
    void y_n_정상_입력_테스트(String answer) {
        assertThatCode(() -> validator.validateAnswer(answer))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("y/n 외의 문자를 입력하는 경우, IllegalArgumentException이 발생한다.")
    void y_n_외의_문자_입력_테스트() {
        // given
        String answer = "d";

        // when & then
        assertThatThrownBy(() ->
                validator.validateAnswer(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }
}
