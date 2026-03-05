package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러 생성 테스트")
    void 딜러_생성_테스트() {
        // when & then
        assertThatCode(Dealer::new)
                .doesNotThrowAnyException();
    }

}
