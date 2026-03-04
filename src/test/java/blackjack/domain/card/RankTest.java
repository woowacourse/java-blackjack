package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("ACE 여부를 확인할 수 있다")
    void isAce() {
        assertThat(Rank.ACE.isAce()).isTrue();
        assertThat(Rank.KING.isAce()).isFalse();
    }
}
