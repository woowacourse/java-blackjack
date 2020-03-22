package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {


    @Test
    @DisplayName("두명의 점수가 같은 경우 무승부 : 수익이 0으로 같음")
    void isSame() {
        assertThat(ResultCalculator.isDraw(15, 15)).isTrue();
        assertThat(ResultCalculator.isDraw(15, 16)).isFalse();
    }

    @Test
    @DisplayName("두명의 점수가 모두 21을 넘기는 경우 :  카드합이 21이 넘은 플레이어는 패,  21 이하인 플레이어는 승")
    void testWhenBothOverBlackJack() {
        assertThat(ResultCalculator.isPlayerLose(25, 15)).isFalse();
        assertThat(ResultCalculator.isPlayerLose(25, 34)).isTrue();
    }

    @Test
    @DisplayName("플레이어만 21을 넘기는 경우")
    void testWhenOnlyPlayerOverBlackJack() {
        assertThat(ResultCalculator.isPlayerLose(20, 34)).isTrue();
    }

    @Test
    @DisplayName("딜러만 21을 넘기는 경우")
    void testWhenOnlyDealerOverBlackJack() {
        assertThat(ResultCalculator.isPlayerLose(25, 14)).isFalse();
    }

    @Test
    @DisplayName("딜러와 플레이어 둘 다 21이하인 경우")
    void testWhenBothLowerThanBlackJack() {
        assertThat(ResultCalculator.isPlayerLose(15, 14)).isTrue();
        assertThat(ResultCalculator.isPlayerLose(15, 20)).isFalse();
    }
}


