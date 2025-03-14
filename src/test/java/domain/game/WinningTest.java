package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
class WinningTest {

    @Test
    void 플레이어가_21점을_넘으면_버스트로_패배() {
        Winning winning = Winning.determine(22, 20);
        assertThat(winning).isEqualTo(Winning.LOSE);
    }

    @Test
    void 플레이어와_딜러_모두_버스트인_경우_플레이어_패배(){
        Winning winning = Winning.determine(22, 23);
        assertThat(winning).isEqualTo(Winning.LOSE);
    }

    @Test
    void 플레이어가_21점이하고_딜러가_21점을_넘으면_딜러_버스트로_승리() {
        Winning winning = Winning.determine(21, 23);
        assertThat(winning).isEqualTo(Winning.WIN);
    }

    @ParameterizedTest
    @CsvSource({"20,19,WIN", "20,20,DRAW", "19,20,LOSE"})
    void 플레이어와_딜러가_모두_버스트가_아닐경우_점수_비교후_승무패_결정(int playerScore, int dealerScore, Winning playerWinning) {
        Winning winning = Winning.determine(playerScore, dealerScore);
        assertThat(winning).isEqualTo(playerWinning);
    }
}
