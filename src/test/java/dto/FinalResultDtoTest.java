package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestUtil.createResult;
import static util.TestUtil.createResults;

import domain.WinningStatus;
import domain.result.Results;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FinalResultDtoTest {

    @ParameterizedTest
    @CsvSource(value = {"WIN,-1000,1000", "BLACKJACK_WIN,-1500,1500", "LOSE,1000,-1000", "DRAW,0,0"})
    void Results로_FinalResultDto를_생성한다(WinningStatus winningStatus,
                                       long expectedDealerProfit,
                                       long expectedPlayerProfit) {
        // given, when
        Results results = createResults(createResult("봉구스", 1000, winningStatus));
        FinalResultDto finalResultDto = FinalResultDto.from(results);
        // then
        assertEquals(expectedDealerProfit, finalResultDto.dealerProfit());
        assertEquals(expectedPlayerProfit, finalResultDto.playerResultDtos().getFirst().profit());
    }


    @Test
    void 딜러의_수익을_계산한다() {
        // given
        Results results = createResults(createResult("루디", 10000, WinningStatus.WIN),
                createResult("시오", 1000, WinningStatus.BLACKJACK_WIN),
                createResult("영기", 100, WinningStatus.LOSE),
                createResult("제이크", 10, WinningStatus.DRAW));
        FinalResultDto finalResultDto = FinalResultDto.from(results);

        // when, then
        assertEquals(-10000 - 1500 + 100, finalResultDto.dealerProfit());
    }
}
