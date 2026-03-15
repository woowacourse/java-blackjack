package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class GameResultTest {

    @ParameterizedTest
    @EnumSource(GameResult.class)
    void 게임결과는_승__블랙잭승_무_패_만있다(GameResult gameResult) {
        assertTrue(gameResult == GameResult.WIN
                || gameResult == GameResult.BACKJACK_WIN
                || gameResult == GameResult.TIE
                || gameResult == GameResult.LOSE);
    }

    @Test
    void 블랙잭_승리시_베팅금액의_1_5배를_수익으로_받는다() {
        assertThat(GameResult.BACKJACK_WIN.calculateIncome(1000)).isEqualTo(1500);
    }

    @Test
    void 일반_승리시_베팅금액만큼_수익을_받는다() {
        assertThat(GameResult.WIN.calculateIncome(1000)).isEqualTo(1000);
    }

    @Test
    void 무승부시_수익은_0이다() {
        assertThat(GameResult.TIE.calculateIncome(1000)).isEqualTo(0);
    }

    @Test
    void 패배시_베팅금액만큼_손실이다() {
        assertThat(GameResult.LOSE.calculateIncome(1000)).isEqualTo(-1000);
    }
}
