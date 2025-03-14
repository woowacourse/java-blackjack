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
    void 플레이어와_딜러_모두_블랙잭일경우_무승부() {
        Winning winning = Winning.determine(new ScoreInfo(21,2), new ScoreInfo(21,2));
        assertThat(winning).isEqualTo(Winning.DRAW);
    }

    @Test
    void 플레이어만_블랙잭일_경우_플레이어_블랙잭() {
        Winning winning = Winning.determine(new ScoreInfo(21,2), new ScoreInfo(20,2));
        assertThat(winning).isEqualTo(Winning.BLACKJACK);
    }

    @Test
    void 딜러만_블랙잭일_경우_플레이어_패배() {
        Winning winning = Winning.determine(new ScoreInfo(20,2), new ScoreInfo(21,2));
        assertThat(winning).isEqualTo(Winning.LOSE);
    }

    @Test
    void 플레이어가_21점을_넘으면_버스트로_패배() {
        Winning winning = Winning.determine(new ScoreInfo(22,3), new ScoreInfo(12,2));
        assertThat(winning).isEqualTo(Winning.LOSE);
    }

    @Test
    void 플레이어와_딜러_모두_버스트인_경우_플레이어_패배(){
        Winning winning = Winning.determine(new ScoreInfo(22,3), new ScoreInfo(23,3));
        assertThat(winning).isEqualTo(Winning.LOSE);
    }

    @Test
    void 플레이어가_21점이하고_딜러가_21점을_넘으면_딜러_버스트로_승리() {
        Winning winning = Winning.determine(new ScoreInfo(20,2), new ScoreInfo(23,3));
        assertThat(winning).isEqualTo(Winning.WIN);
    }

    @ParameterizedTest
    @CsvSource({"20,2,19,2,WIN", "20,2,20,2,DRAW", "19,2,20,2,LOSE"})
    void 플레이어와_딜러가_모두_버스트와_블랙잭_아닐경우_점수_비교후_승무패_결정(int playerScore, int playerCardSize, int dealerScore, int dealerCardSize, Winning playerWinning) {
        Winning winning = Winning.determine(new ScoreInfo(playerScore, playerCardSize), new ScoreInfo(dealerScore, dealerCardSize));
        assertThat(winning).isEqualTo(playerWinning);
    }
}
