package domain;

import static domain.HandsTestFixture.blackJack;
import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.sum17Size3One;
import static domain.HandsTestFixture.sum17Size3Two;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("카드 합이 같고 카드 갯수가 같으면 무승부이다.")
    @Test
    void isTie() {
        Assertions.assertThat(sum17Size3One.calculateResult(sum17Size3Two)).isEqualTo(Result.TIE);
    }

    @DisplayName("카드 합이 같은데 카드 갯수가 더 적으면 승리이다.")
    @Test
    void isWinBySize() {
        Assertions.assertThat(sum20Size2.calculateResult(sum20Size3)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("카드 합이 21이하이면서 21에 가까운 카드가 승리한다.")
    void isWin() {
        Assertions.assertThat(sum21Size3.calculateResult(sum20Size2)).isEqualTo(Result.WIN);
        Assertions.assertThat(sum20Size2.calculateResult(sum21Size3)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("카드 합이 21초과이면 패배한다.")
    void isLoseWhenCardSumGreater21() {
        Assertions.assertThat(bustHands.calculateResult(sum20Size2)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("blackjack이 이긴다.")
    void isWinBlackJack() {
        Assertions.assertThat(blackJack.calculateResult(sum20Size2)).isEqualTo(Result.BLACK_JACK_WIN);
        Assertions.assertThat(sum20Size2.calculateResult(blackJack)).isEqualTo(Result.LOSE);
    }
}
