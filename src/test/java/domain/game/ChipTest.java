package domain.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChipTest {

    @ParameterizedTest
    @CsvSource(value = {"LOSE,1000,-1000", "DRAW,1000,0", "WIN,1000,1000", "BLACKJACK_WIN,1000,1500"})
    void 게임_결과에_따라_배팅_결과를_계산한다(GameResult gameResult, int bettingAmount, int expected) {
        //given
        Chip chip = new Chip(bettingAmount);

        //when
        Chip actual = chip.calculateBettingAmount(gameResult);

        //then
        assertThat(actual.getBettingAmount()).isEqualTo(expected);
    }
}
