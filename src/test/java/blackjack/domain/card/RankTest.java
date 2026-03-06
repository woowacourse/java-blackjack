package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("ACE 카드는 isAce가 참이다")
    void isAce_ShouldReturnTrue_ForAce() {
        assertThat(Rank.ACE.isAce()).isTrue();
    }

    @Test
    @DisplayName("ACE가 아닌 카드는 isAce가 거짓이다")
    void isAce_ShouldReturnFalse_ForNonAce() {
        assertThat(Rank.KING.isAce()).isFalse();
    }

    @Test
    @DisplayName("ACE 바로 다음 순서인 TWO는 isAce가 거짓이다")
    void isAce_ShouldReturnFalse_ForTwo() {
        assertThat(Rank.TWO.isAce()).isFalse();
    }
}
