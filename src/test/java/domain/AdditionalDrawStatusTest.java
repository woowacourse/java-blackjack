package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdditionalDrawStatusTest {

    @Test
    @DisplayName("추가 드로우 상태를 받아서 드로우면 true를 반환한다.")
    void isDrawableTest() {
        Assertions.assertThat(AdditionalDrawStatus.isDrawable(AdditionalDrawStatus.DRAW)).isTrue();
    }
}
