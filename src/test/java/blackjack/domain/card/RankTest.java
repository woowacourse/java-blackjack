package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("카드 숫자는 13종류이다")
    void rankCount() {
        assertThat(Rank.values()).hasSize(13);
    }

    @ParameterizedTest
    @DisplayName("J, Q, K 의 점수는 10이다")
    @CsvSource({"JACK, 10", "QUEEN, 10", "KING, 10"})
    void faceCardValue(Rank rank, int expected) {
        assertThat(rank.getValue()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("숫자 카드(2~10)는 카드에 표시된 숫자가 점수이다")
    @CsvSource({"TWO,2", "FIVE,5", "TEN,10"})
    void numberCardValue(Rank rank, int expected) {
        assertThat(rank.getValue()).isEqualTo(expected);
    }

    @Test
    @DisplayName("ACE의 기본 점수는 1이다")
    void aceDefaultValue() {
        assertThat(Rank.ACE.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("ACE 여부를 확인할 수 있다")
    void isAce() {
        assertThat(Rank.ACE.isAce()).isTrue();
        assertThat(Rank.KING.isAce()).isFalse();
    }
}
