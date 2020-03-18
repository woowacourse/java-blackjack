package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class YesOrNoTest {

    @Test
    @DisplayName("y면 true, n면 false 리턴 테스트")
    void isYes() {
        Assertions.assertThat(YesOrNo.of("y")).isEqualTo(YesOrNo.YES);
        Assertions.assertThat(YesOrNo.of("Y")).isEqualTo(YesOrNo.YES);
        Assertions.assertThat(YesOrNo.of("n")).isEqualTo(YesOrNo.NO);
        Assertions.assertThat(YesOrNo.of("N")).isEqualTo(YesOrNo.NO);
    }
}
