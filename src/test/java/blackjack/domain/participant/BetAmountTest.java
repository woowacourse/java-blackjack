package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    private BetAmount betAmount;

    @BeforeEach
    void setUp() {
        this.betAmount = new BetAmount(1_000);
    }

    @Test
    @DisplayName("베팅 금액이 성공적으로 생성되는지 테스트")
    void testBetAmountCreate() {
        assertThat(betAmount.getValue()).isEqualTo(1_000);
    }

    @Test
    @DisplayName("블랙잭에 따른 결과로 곱해진 값을 잘 반환하는지 테스트")
    void testBlackjackBetAmountMultiply() {
        assertThat(this.betAmount.multiply(1.5)).isEqualTo(1_500);
    }

    @Test
    @DisplayName("버스트 혹은 패배시 곱해진 값을 잘 반환하는지 테스트")
    void testBustOrLostBetAmountMultiply() {
        assertThat(this.betAmount.multiply(-1)).isEqualTo(-1_000);
    }

    @Test
    @DisplayName("초기 베팅 금액이 0보다 작을 경우 예외처리")
    void testNegativeException() {
        assertThatThrownBy(() -> new BetAmount(-3_000))
            .isInstanceOf(IllegalArgumentException.class);
    }
}