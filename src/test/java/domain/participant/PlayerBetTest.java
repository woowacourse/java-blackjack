package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerBetTest {

    @Nested
    @DisplayName("craete() 테스트")
    class CreateMethodTest {

        @Test
        @DisplayName("유효한 배팅 금액을 전달하면 PlayerBet을 생성한다")
        void create_givenValidMoney_thenReturnPlayerBet() {
            PlayerBet playerBet = assertDoesNotThrow(() -> PlayerBet.create(1000));

            assertThat(playerBet)
                    .isExactlyInstanceOf(PlayerBet.class);
        }

        @Test
        @DisplayName("최소 금액을 배팅하지 않으면 예외가 발생한다")
        void create_givenLessThanMinimumMoney_thenFail() {
            assertThatThrownBy(() -> PlayerBet.create(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최소 천 원 이상 배팅해주세요.");
        }

        @Test
        @DisplayName("정해진 금액 단위로 배팅하지 않으면 예외가 발생한다")
        void create_givenInvalidAmountUnit_thenFail() {
            assertThatThrownBy(() -> PlayerBet.create(1100))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("천 원 단위로 배팅해주세요.");
        }
    }

    @Test
    @DisplayName("calculateBenefit()은 배당 금액을 건네주면 순이익을 반환한다")
    void calculateBenefit_givenPrizeRatio_thenReturnBenefit() {
        // given
        final PlayerBet playerBet = PlayerBet.create(1000);
        final double prizeRatio = 1.5d;

        // when
        final BigDecimal actual = playerBet.calculateBenefit(prizeRatio);

        // then
        final BigDecimal expected = new BigDecimal("500.0");

        assertThat(actual)
                .isEqualTo(expected);
    }
}
