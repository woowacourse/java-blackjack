package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.vo.Bet;
import domain.vo.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    private static final double NUMBER_TO_MULTIPLE_WHEN_DEFEAT = -1D;
    private static final double NUMBER_TO_MULTIPLE_WHEN_BLACKJACK_WIN = 1.5D;
    private static final double NUMBER_TO_MULTIPLE_WHEN_NORMAL_WIN = 1D;
    private static final double DRAW_VALUE = 0D;

    @Test
    @DisplayName("win 생성 테스트")
    public void testVictoryCreate() {
        //given
        final Bet bet = Bet.of(10D);

        //when
        final Profit profit = Profit.victory(bet);

        //then
        assertThat(profit.getValue()).isEqualTo(bet.getValue() * NUMBER_TO_MULTIPLE_WHEN_NORMAL_WIN);
    }

    @Test
    @DisplayName("blackJackWin 생성 테스트")
    public void testBlackJackVictoryCreate() {
        //given
        final Bet bet = Bet.of(10D);

        //when
        final Profit profit = Profit.blackJackVictory(bet);

        //then
        assertThat(profit.getValue()).isEqualTo(bet.getValue() * NUMBER_TO_MULTIPLE_WHEN_BLACKJACK_WIN);
    }

    @Test
    @DisplayName("defeat 생성 테스트")
    public void testDefeatCreate() {
        //given
        final Bet bet = Bet.of(10D);

        //when
        final Profit profit = Profit.defeat(bet);

        //then
        assertThat(profit.getValue()).isEqualTo(bet.getValue() * NUMBER_TO_MULTIPLE_WHEN_DEFEAT);
    }

    @Test
    @DisplayName("draw 생성 테스트")
    public void testDrawCreate() {
        //given
        //when
        final Profit profit = Profit.draw();

        //then
        assertThat(profit.getValue()).isEqualTo(DRAW_VALUE);
    }
}
