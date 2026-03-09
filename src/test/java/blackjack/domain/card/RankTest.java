package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @Test
    void ACE_카드는_isAce가_참이다() {
        assertThat(Rank.ACE.isAce()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"})
    void ACE가_아닌_카드는_isAce가_거짓이다(Rank rank) {
        assertThat(rank.isAce()).isFalse();
    }
}
