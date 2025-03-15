package model.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitOptionTest {

    @DisplayName("예외: 지정된 옵션 값만 유효하다.")
    @Test
    void checkOption() {
        assertThatThrownBy(() -> HitOption.isNo("X"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 옵션 값입니다.");
    }

    @DisplayName("히트 옵션이 n인지 확인한다.")
    @Test
    void isNo() {
        assertThat(HitOption.isNo("n")).isTrue();
    }
}
