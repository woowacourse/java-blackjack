package domain;

import static domain.HandsTestFixture.blackJack;
import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.sum17Size3One;
import static domain.HandsTestFixture.sum17Size3Two;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("카드 합이 같다면 무승부이다.")
    @Test
    void isTie() {
        assertAll(
                () -> assertThat(sum17Size3One.calculateResult(sum17Size3Two)).isEqualTo(GameResult.TIE),
                () -> assertThat(sum20Size2.calculateResult(sum20Size3)).isEqualTo(GameResult.TIE)
        );
    }

    @Test
    @DisplayName("카드 합이 21이하이면서 21에 가까운 카드가 승리한다.")
    void isWin() {
        assertAll(
                () -> assertThat(sum21Size3.calculateResult(sum20Size2)).isEqualTo(GameResult.WIN),
                () -> assertThat(sum20Size2.calculateResult(sum21Size3)).isEqualTo(GameResult.LOSE));
    }

    @Test
    @DisplayName("카드 합이 21초과이면 패배한다.")
    void isLoseWhenCardSumGreater21() {
        assertAll(
                () -> assertThat(bustHands.calculateResult(sum20Size2)).isEqualTo(GameResult.LOSE),
                () -> assertThat(sum20Size2.calculateResult(bustHands)).isEqualTo(GameResult.WIN));
    }

    @Test
    @DisplayName("blackjack이 이긴다.")
    void isWinBlackJack() {
        assertAll(
                () -> assertThat(blackJack.calculateResult(sum20Size2)).isEqualTo(GameResult.BLACK_JACK_WIN),
                () -> assertThat(sum20Size2.calculateResult(blackJack)).isEqualTo(GameResult.LOSE));
    }
}
