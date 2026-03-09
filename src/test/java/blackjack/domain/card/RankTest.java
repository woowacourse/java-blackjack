package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void ACE_카드는_isAce가_참이다() {
        assertThat(Rank.ACE.isAce()).isTrue();
    }

    @Test
    void ACE가_아닌_카드는_isAce가_거짓이다() {
        assertThat(Rank.KING.isAce()).isFalse();
    }

    @Test
    void ACE_바로_다음_순서인_TWO는_isAce가_거짓이다() {
        assertThat(Rank.TWO.isAce()).isFalse();
    }
}
