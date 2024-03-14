package domain;

import static domain.HandsTestFixture.blackJack;
import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.sum10Size2;
import static domain.HandsTestFixture.sum17Size3One;
import static domain.HandsTestFixture.sum17Size3Two;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("카드 합이 같고 카드 갯수가 같으면 무승부이다.")
    @Test
    void isTie() {
        Assertions.assertThat(sum17Size3One.calculateResultBy(sum17Size3Two)).isEqualTo(Result.TIE);
    }

    @DisplayName("카드 합이 같은데 카드 갯수가 더 적으면 승리이다.")
    @Test
    void isWinBySize() {
        Assertions.assertThat(sum20Size2.calculateResultBy(sum20Size3)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("카드 합이 21이하이면서 21에 가까운 카드가 승리한다.")
    void isWin() {
        Assertions.assertThat(sum20Size2.calculateResultBy(sum10Size2)).isEqualTo(Result.WIN);
        Assertions.assertThat(sum20Size2.calculateResultBy(sum21Size2)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("카드 합이 21초과이면 패배한다.")
    void isLoseWhenCardSumGreater21() {
        Assertions.assertThat(bustHands.calculateResultBy(sum20Size2)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("blackjack이면 WIN_BLACKJACK이다.")
    void isWinBlackJack() {
        Assertions.assertThat(blackJack.calculateResultBy(sum20Size2)).isEqualTo(Result.WIN_BLACKJACK);
        Assertions.assertThat(sum20Size2.calculateResultBy(blackJack)).isEqualTo(Result.LOSE);
    }
}
