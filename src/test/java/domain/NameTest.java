package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    @DisplayName("플레이어 이름의 길이 20자 초과 예외 발생 테스트")
    void 길이_초과_예외발생테스트() {
        String exceptionName = "ddddddddddddddddddddd";
        assertThatThrownBy(() -> Name.from(exceptionName)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Null 혹은 빈 값 입력 시 예외 발생 테스트")
    void Null값_혹은_빈값_예외발생테스트() {
        String blankName = " ";
        String emptyName = "";
        assertThatThrownBy(() -> Name.from(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Name.from(blankName)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Name.from(emptyName)).isInstanceOf(IllegalArgumentException.class);
    }

}