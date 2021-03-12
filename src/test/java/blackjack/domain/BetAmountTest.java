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

    @Test
    @DisplayName("특정 a값을 넣었을 때, 음수로 변환해서 -a를 리턴하는 지 테스트")
    public void toNegative() {
        BetAmount betAmount = new BetAmount(1);
        assertThat(betAmount.toNegative()).isEqualTo(new BetAmount(-1));
    }
}