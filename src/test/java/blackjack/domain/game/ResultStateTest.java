package blackjack.domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ResultStateTest {

    @Nested
    @DisplayName("첫번째 점수가 21점이고")
    class resultPlayerTestByFirstScore21 {

        final Score firstScore = Score.from(21);
        Score secondScore;

        @Test
        @DisplayName("두번째 점수가 21점 미만일 때, 블랙잭을 반환하는지 테스트")
        void SecondScoreLess21() {
            secondScore = Score.from(20);

            Assertions.assertThat(ResultState.resultPlayer(firstScore, secondScore, 2)).isEqualTo(ResultState.BLACKJACK);
        }

        @Test
        @DisplayName("두번째 점수가 21점일 때, 무승부를 반환하는지 테스트")
        void SecondScoreEqual21() {
            secondScore = Score.from(21);

            Assertions.assertThat(ResultState.resultPlayer(firstScore, secondScore, 2)).isEqualTo(ResultState.TIE);
        }

        @Test
        @DisplayName("두번째 점수가 21점 초과일 때, 블랙잭를 반환하는지 테스트")
        void SecondScoreGreater21() {
            secondScore = Score.from(22);

            Assertions.assertThat(ResultState.resultPlayer(firstScore, secondScore, 2)).isEqualTo(ResultState.BLACKJACK);
        }
    }

    @Nested
    @DisplayName("첫번째 점수가 20점이고")
    class resultPlayerTestByFirstScore20 {

        final Score firstScore = Score.from(20);
        Score secondScore;

        @Test
        @DisplayName("두번째 점수가 20점 미만일 때, 승리를 반환하는지 테스트")
        void SecondScoreLess21() {
            secondScore = Score.from(19);

            Assertions.assertThat(ResultState.resultPlayer(firstScore, secondScore,3)).isEqualTo(ResultState.WIN);
        }

        @Test
        @DisplayName("두번째 점수가 21점 초과일 때, 승리를 반환하는지 테스트")
        void SecondScoreGreater21() {
            secondScore = Score.from(22);

            Assertions.assertThat(ResultState.resultPlayer(firstScore, secondScore, 3)).isEqualTo(ResultState.WIN);
        }

        @Test
        @DisplayName("두번째 점수가 20점일 때, 무승부를 반환하는지 테스트")
        void SecondScoreEqual20() {
            secondScore = Score.from(20);

            Assertions.assertThat(ResultState.resultPlayer(firstScore, secondScore, 3)).isEqualTo(ResultState.TIE);
        }

        @Test
        @DisplayName("두번째 점수가 21점일 때, 패배를 반환하는지 테스트")
        void SecondScoreEqual21() {
            secondScore = Score.from(21);

            Assertions.assertThat(ResultState.resultPlayer(firstScore, secondScore, 3)).isEqualTo(ResultState.LOSE);
        }
    }

    @Nested
    @DisplayName("첫번째 점수가 22점이고")
    class resultPlayerTestByFirstScore22 {

        final Score firstScore = Score.from(22);
        Score secondScore;

        @Test
        @DisplayName("두번째 점수가 21점 이하일 때, 패배를 반환하는지 테스트")
        void SecondScoreLess21() {
            secondScore = Score.from(21);

            Assertions.assertThat(ResultState.resultPlayer(firstScore, secondScore, 3)).isEqualTo(ResultState.LOSE);
        }

        @Test
        @DisplayName("두번째 점수가 21점 초과일 때, 무승부를 반환하는지 테스트")
        void SecondScoreGreater21() {
            secondScore = Score.from(22);

            Assertions.assertThat(ResultState.resultPlayer(firstScore, secondScore, 3)).isEqualTo(ResultState.TIE);
        }
    }

    @Test
    @DisplayName("게임 결과가 블랙잭일 때, 베팅 금액의 1.5배를 반환하는지 확인")
    void calculateProfitTestByBlackjack() {
        final Betting betting = Betting.from(10000);

        Assertions.assertThat(ResultState.BLACKJACK.calculateProfit(betting.getValue())).isEqualTo(15000);
    }

    @Test
    @DisplayName("게임 결과가 승리일 때, 베팅 금액의 1배를 반환하는지 확인")
    void calculateProfitTestByWin() {
        final Betting betting = Betting.from(10000);

        Assertions.assertThat(ResultState.WIN.calculateProfit(betting.getValue())).isEqualTo(10000);
    }


    @Test
    @DisplayName("게임 결과가 무승부일 때, 베팅 금액의 0배를 반환하는지 확인")
    void calculateProfitTestByTie() {
        final Betting betting = Betting.from(10000);

        Assertions.assertThat(ResultState.TIE.calculateProfit(betting.getValue())).isEqualTo(0);
    }


    @Test
    @DisplayName("게임 결과가 패배일 때, 베팅 금액의 -1배를 반환하는지 확인")
    void calculateProfitTestByLose() {
        Betting betting = Betting.from(10000);

        Assertions.assertThat(ResultState.LOSE.calculateProfit(betting.getValue())).isEqualTo(-10000);
    }
}
