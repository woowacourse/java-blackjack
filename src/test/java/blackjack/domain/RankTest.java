package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void 범위에_맞는_숫자가_들어오면_숫자_그대로_반환한다() {
        String input = "2";
        Rank two = Rank.of(input);
        assertThat(two).isEqualTo(Rank.TWO);
    }

    @Test
    void A가_들어오면_ACE을_반환한다() {
        String input = "A";
        Rank ace = Rank.of(input);
        assertThat(ace).isEqualTo(Rank.ACE);
    }

    @Test
    void J가_들어오면_JACK을_반환한다() {
        String input = "J";
        Rank jack = Rank.of(input);
        assertThat(jack).isEqualTo(Rank.JACK);
    }

    @Test
    void Q가_들어오면_QUEEN을_반환한다() {
        String input = "Q";
        Rank queen = Rank.of(input);
        assertThat(queen).isEqualTo(Rank.QUEEN);

    }

    @Test
    void K가_들어오면_KING을_반환한다() {
        String input = "K";
        Rank king = Rank.of(input);
        assertThat(king).isEqualTo(Rank.KING);
    }


    @Test
    void 랭크에_해당하지_않는_문자가_들어오면_예외_처리한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            Rank.of("1");
        });
    }
}