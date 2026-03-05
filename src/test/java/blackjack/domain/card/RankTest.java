package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("ACE 카드는 isAce가 참이다")
    void ACE_카드는_isAce가_참이다() {
        assertThat(Rank.ACE.isAce()).isTrue();
    }

    @Test
    @DisplayName("ACE가 아닌 카드는 isAce가 거짓이다")
    void ACE가_아닌_카드는_isAce가_거짓이다() {
        assertThat(Rank.KING.isAce()).isFalse();
    }

    @Test
    @DisplayName("ACE 바로 다음 순서인 TWO는 isAce가 거짓이다")
    void ACE_바로_다음_순서인_TWO는_isAce가_거짓이다() {
        assertThat(Rank.TWO.isAce()).isFalse();
    }
}
