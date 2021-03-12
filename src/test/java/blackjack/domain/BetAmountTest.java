package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BetAmountTest {

    @Test
    @DisplayName("같은 값을 가지면 같은 객체로 인식하는 지 테스트")
    public void equals() {
        assertThat(new BetAmount(1)).isEqualTo(new BetAmount(1));
    }
}