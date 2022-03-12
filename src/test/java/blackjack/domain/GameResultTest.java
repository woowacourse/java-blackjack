package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {

    @DisplayName("카드 점수에 따라 승에 해당하는 객체를 반환하는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"21, 20", "11,0"})
    void win(final int dealerScore, final int gamblerScore) {
        //when
        final GameResult result = GameResult.of(dealerScore, gamblerScore);

        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @DisplayName("카드 점수에 따라 패에 해당하는 객체를 반환하는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"11, 20", "0,11"})
    void lose(final int dealerScore, final int gamblerScore) {
        //when
        final GameResult result = GameResult.of(dealerScore, gamblerScore);

        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("카드 점수에 따라 무승부에 해당하는 객체를 반환하는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"21, 21", "11,11"})
    void draw(final int dealerScore, final int gamblerScore) {
        //when
        final GameResult result = GameResult.of(dealerScore, gamblerScore);

        assertThat(result).isEqualTo(GameResult.DRAW);
    }
}
