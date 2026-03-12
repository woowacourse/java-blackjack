package domain.result;

import static util.TestUtil.createAmount10000Result;

import domain.Money;
import domain.WinningStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultTest {

    @ParameterizedTest
    @CsvSource(value = {"WIN,10000", "BLACKJACK_WIN,15000", "LOSE,-10000", "DRAW,0"})
    void 게임_결과로_수익을_계산한다(WinningStatus winningStatus, long expectedAmount) {
        // given
        Result result = createAmount10000Result("봉구스", winningStatus);

        // when, then
        Assertions.assertEquals(new Money(expectedAmount), result.getProfit());
    }
}
