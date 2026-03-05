package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TrumpCardTest {

    @Test
    void 랭크가_null_이면_예외_발생한다() {
        Assertions.assertThatThrownBy(() -> TrumpCard.of(Suit.of("하트"), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rank와 Suit 중 하나라도 null이 올 수 없습니다.");
    }

    @Test
    void 수트가_null_이면_예외_발생한다() {
        Assertions.assertThatThrownBy(() -> TrumpCard.of(null, Rank.of("2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rank와 Suit 중 하나라도 null이 올 수 없습니다.");
    }

    @Test
    void 랭크와_수트_둘_다_null_이면_예외_발생한다() {
        Assertions.assertThatThrownBy(() -> TrumpCard.of(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rank와 Suit 중 하나라도 null이 올 수 없습니다.");
    }

    @Test
    void 조건에_맞는_랭크와_수트가_들어오면_정상_생성된다() {
        TrumpCard trumpCard = TrumpCard.of(Suit.of("하트"), Rank.of("3"));
        assertThat(trumpCard).isNotNull();
    }
}