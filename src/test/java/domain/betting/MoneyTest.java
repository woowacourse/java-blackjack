package domain.betting;

import static domain.betting.Money.INVALID_BETTING_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.game.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("돈은 0 이상이다.")
    void createSuccess() {
        assertThatCode(() -> new Money(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("0 미만으로 생성하면, 예외가 발생한다.")
    void createFail() {
        assertThatCode(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_BETTING_MESSAGE);
    }

    @Test
    @DisplayName("배율을 곱한 Money를 반환한다.")
    void create() {
        int betMoney = 20000;
        Money money = new Money(betMoney);

        Money PlayerWinProfit = money.multiply(Result.PLAYER_WIN.getProfitRate());
        Money DealerWinProfit = money.multiply(Result.DEALER_WIN.getProfitRate());
        Money PushProfit = money.multiply(Result.PUSH.getProfitRate());
        Money BLACK_JACK_Profit = money.multiply(Result.PLAYER_BLACK_JACK.getProfitRate());

        assertAll(
                () -> assertEquals(betMoney * 1, PlayerWinProfit.getValue()),
                () -> assertEquals(betMoney * -1, DealerWinProfit.getValue()),
                () -> assertEquals(betMoney * 0, PushProfit.getValue()),
                () -> assertEquals(betMoney * 1.5, BLACK_JACK_Profit.getValue())
        );
    }

}
