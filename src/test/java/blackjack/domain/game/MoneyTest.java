package blackjack.domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {

    @ParameterizedTest
    @CsvSource(value = {"LOSE:-1", "WIN:1", "DRAW:0", "BLACK_JACK:1.5"}, delimiter = ':')
    void 금액은_게임_결과에_대해_수익을_반환한다(final GameResult result, final double multiple) {
        //given
        int origin_money = 10000000;
        Money money = new Money(origin_money);

        //when
        int profit = money.getProfit(result);

        //then
        Assertions.assertThat(profit).isEqualTo((int) (origin_money * multiple));
    }
}