package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    @DisplayName("이름의 길이가 MAX 길이 이하고 비어있지 않은 경우 정상 처리돼야 한다.")
    void 이름_길이_검증_성공() {
        // given
        String name = "aaaa";

        // when - then
        assertDoesNotThrow(() -> Validator.validateNameLength(name));
    }

    @Test
    @DisplayName("이름의 길이가 MAX 길이 이상인 경우 에러가 발생해야 한다.")
    void 이름_길이_최대길이_에러() {
        // given
        String name = "aaaaaaaaaaaaa";

        // when - then
        assertThatThrownBy(() -> Validator.validateNameLength(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("이름의 길이가 비어있는 경우 에러가 발생해야 한다.")
    void 이름_길이_빈문자_에러() {
        // given
        String name = "";

        // when - then
        assertThatThrownBy(() -> Validator.validateNameLength(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("이름이 영문자로만 이루어진 경우 정상 처리돼야 한다.")
    void 이름_영문자_검증_성공() {
        // given
        String name = "aaaaaaaaa";

        // when - then
        assertDoesNotThrow(() -> Validator.validateNameEng(name));
    }

    @Test
    @DisplayName("이름에 숫자가 포함되어있는 경우 에러가 발생해야 한다.")
    void 이름_숫자_포함_검증_에러() {
        // given
        String name = "aaaa11";

        // when - then
        assertThatThrownBy(() -> Validator.validateNameEng(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 영문자만 포함되어야 합니다.");
    }

    @Test
    @DisplayName("이름에 특수문자가 포함되어있는 경우 에러가 발생해야 한다.")
    void 이름_특수문자_포함_검증_에러() {
        // given
        String name = "aaa!";

        // when - then
        assertThatThrownBy(() -> Validator.validateNameEng(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 영문자만 포함되어야 합니다.");
    }

    @Test
    @DisplayName("돈이 양수인 경우 정상 처리돼야 한다.")
    void 돈_양수_검증_에러() {
        // given
        Integer money = 10;

        // when - then
        assertDoesNotThrow(() -> Validator.validateMoney(money));
    }

    @Test
    @DisplayName("돈이 음수인 경우 에러가 발생해야 한다.")
    void 돈_음수_검증_에러() {
        // given
        Integer money = -10;

        // when - then
        assertThatThrownBy(() -> Validator.validateMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다. 다시 입력해주세요.");
    }
}
