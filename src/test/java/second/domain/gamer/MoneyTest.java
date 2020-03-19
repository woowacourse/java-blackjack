package second.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import second.domain.result.ResultType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static second.domain.result.ResultType.*;

public class MoneyTest {

    @Test
    void initailize() {
        final Money money = new Money(10);
        assertThat(money).isInstanceOf(Money.class);
    }

    // TODO : 리팩토링 가능
    @Test
    @DisplayName("수익률 계산")
    @MethodSource("generateResultType")
    void times() {
        final Money money = new Money(10);

        Money onlyPlayerBlackJackProfit = (Money) money.times(ONLY_PLAYER_BLACK_JACK.getProfitMultipleValue());
        Money bothBlackJackProfit = money.times(DRAW.getProfitMultipleValue());
        Money winProfit = money.times(WIN.getProfitMultipleValue());
        Money loseProfit = money.times(LOSE.getProfitMultipleValue());

        assertThat(onlyPlayerBlackJackProfit).isEqualTo(new Money(15));
        assertThat(bothBlackJackProfit).isEqualTo(new Money(0));
        assertThat(winProfit).isEqualTo(new Money(10));
        assertThat(loseProfit).isEqualTo(new Money(-10));
    }
}
