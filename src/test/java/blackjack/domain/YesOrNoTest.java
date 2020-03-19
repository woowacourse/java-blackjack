package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class YesOrNoTest {

    @Test
    @DisplayName("y면 true, n면 false 리턴 테스트")
    void isYes() {
        assertThat(YesOrNo.of("y")).isEqualTo(YesOrNo.YES);
        assertThat(YesOrNo.of("Y")).isEqualTo(YesOrNo.YES);
        assertThat(YesOrNo.of("n")).isEqualTo(YesOrNo.NO);
        assertThat(YesOrNo.of("N")).isEqualTo(YesOrNo.NO);
    }

    @Test
    @DisplayName("생성자 예외 테스트")
    void of() {
        assertThatThrownBy(() -> YesOrNo.of("yes"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("유효");
    }
}
