package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static domain.player.Revenue.defaultWin;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Revenue 은")
public class RevenueTest {

    @ParameterizedTest(name = "{0} 원을 배팅한 후 진다면 수익은 {1} 원이 된다.")
    @CsvSource({
            "1000, -1000",
            "50000, -50000",
    })
    void lose_시_수익은_기존_배팅_금액만큼_차감된_값이다(final int battingAmount, final int revenueAmount) {
        // given
        final BettingMoney money = BettingMoney.of(battingAmount);

        // when
        final Revenue lose = Revenue.lose(money);

        // then
        assertThat(lose.amount()).isEqualTo(revenueAmount);
    }

    @Test
    void draw_시_수익은_0원이다() {
        // when
        final Revenue draw = Revenue.draw(BettingMoney.of(10000));

        // then
        assertThat(draw.amount()).isEqualTo(0);
    }

    @ParameterizedTest(name = "이긴 경우 블랙잭으로 이긴 것이 아니라면, 자신이 배팅한 금액만큼을 수익으로 얻는다. 예를 들어 {0}원을 걸고 이겼다면 수익은 {1}원이다")
    @CsvSource({
            "1000, 1000",
            "5000, 5000",
            "10000, 10000",
    })
    void 이긴_경우_블랙잭이_아니라면_수익은_배팅한_금액과_같다(final int battingAmount, final int revenueAmount) {
        // given
        final BettingMoney money = BettingMoney.of(battingAmount);

        // when
        final Revenue defaultWin = defaultWin(money);

        // then
        assertThat(defaultWin.amount()).isEqualTo(revenueAmount);
    }

    @ParameterizedTest(name = "이긴 경우 블랙잭이라면, 자신이 배팅한 금액의 1.5배를 수익으로 얻는다. 예를 들어 {0}원을 걸고 블랙잭으로 이겼다면 수익은 {1}원이다")
    @CsvSource({
            "1000, 1500",
            "10000, 15000",
    })
    void 이긴_경우_블랙잭이라면_수익은_배팅한_금액의_일점오배와_같다(final int battingAmount, final int revenueAmount) {
        // given
        final BettingMoney money = BettingMoney.of(battingAmount);

        // when
        final Revenue blackJackWin = Revenue.blackJackWin(money);

        // then
        assertThat(blackJackWin.amount()).isEqualTo(revenueAmount);
    }

    private Revenue revenue(final int amount) {
        return defaultWin(BettingMoney.of(amount));
    }

    @Test
    void Revenue_의_총_합을_구할_수_있다() {
        // given
        final Revenue revenue1 = revenue(1000);
        final Revenue revenue2 = revenue(3000);
        final Revenue revenue3 = revenue(5000);

        // when
        final Revenue result = Revenue.total(List.of(revenue1, revenue2, revenue3));

        // then
        assertThat(result.amount()).isEqualTo(9000);
    }

    @Test
    void Revenue_의_부호를_반전시킬_수_있다() {
        // given
        final Revenue revenue = revenue(1000);

        // when
        final Revenue reverse = revenue.reverse();

        // then
        assertThat(reverse.amount()).isEqualTo(-1000);
    }
}
