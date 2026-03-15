package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    void 플레이어_이름_길이_20자_초과_예외발생테스트() {
        String exceptionName = "ddddddddddddddddddddd";
        assertThatThrownBy(() -> Name.from(exceptionName)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Null값_혹은_빈값_예외발생테스트() {
        String blankName = " ";
        String emptyName = "";
        assertThatThrownBy(() -> Name.from(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Name.from(blankName)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Name.from(emptyName)).isInstanceOf(IllegalArgumentException.class);
    }

}
