package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingTest {

    @DisplayName("배팅 금액은 1,000원 이상 100,000원 이하여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 10000, 100000})
    void 배팅_금액은_1000원_이상_100000원_이하여야_한다(final int betAmount) {

        // given

        // when & then
        assertThatCode(() -> new Betting(betAmount))
                .doesNotThrowAnyException();

    }

    @DisplayName("배팅 금액은 1,000원 이상 100,000원 이하가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 100001, -10000})
    void 배팅_금액은_1000원_이상_100000원_이하가_아니라면_예외가_발생한다(final int betAmount) {

        // given

        // when & then
        assertThatThrownBy(() -> new Betting(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("블랙잭이 아니면서 승리 시 배팅한 만큼의 수익을 얻는다.")
    @Test
    void 블랙잭이_아니면서_승리_시_배팅한_만큼의_수익을_얻는다() {

        // given
        final Betting betting = new Betting(10000);

        // when
        final int profit = betting.calculateRegularWinProfit(10000);

        // then
        assertThat(profit).isEqualTo(20000);
    }

    @DisplayName("패배 시 모든 배팅 금액을 잃는다.")
    @Test
    void 패배_시_모든_배팅_금액을_잃는다() {

        // given
        final Betting betting = new Betting(10000);

        // when
        final int profit = betting.calculateRegularLoseProfit(0);

        // then
        assertThat(profit).isEqualTo(-10000);
    }

    @DisplayName("블랙잭 이면서 이길 시 배팅한 만큼의 수익을 얻는다.")
    @Test
    void 블랙잭_이면서_이길_시_배팅한_만큼의_수익을_얻는다() {

        // given
        final Betting betting = new Betting(10000);

        // when
        final int profit = betting.calculateBlackjackWinProfit(10000);

        // then
        assertThat(profit).isEqualTo(25000);
    }
}
