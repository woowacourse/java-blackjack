package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class TrumpCardTest {

    @Test
    void 랭크가_null_이면_예외_발생한다() {
        Assertions.assertThatThrownBy(() -> TrumpCard.of(Suit.of("하트"), null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("카드의 수트와 랭크는 null일 수 없습니다.");
    }

    @Test
    void 수트가_null_이면_예외_발생한다() {
        Assertions.assertThatThrownBy(() -> TrumpCard.of(null, Rank.of("2")))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("카드의 수트와 랭크는 null일 수 없습니다.");
    }

    @Test
    void 랭크와_수트_둘_다_null_이면_예외_발생한다() {
        Assertions.assertThatThrownBy(() -> TrumpCard.of(null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("카드의 수트와 랭크는 null일 수 없습니다.");
    }

    @Test
    void 조건에_맞는_랭크와_수트가_들어오면_정상_생성된다() {
        TrumpCard trumpCard = TrumpCard.of(Suit.of("하트"), Rank.of("3"));
        assertThat(trumpCard).isNotNull();
    }

    @Test
    void 카드가_에이스라면_true를_반환한다() {
        TrumpCard trumpCard = TrumpCard.of(Suit.of("하트"), Rank.of("A"));
        boolean isAce = trumpCard.isAce();
        assertThat(isAce).isTrue();
    }

    @Test
    void 카드의_한글_이름을_반환한다() {
        TrumpCard trumpCard = TrumpCard.of(Suit.of("하트"), Rank.of("A"));
        assertThat(trumpCard.koreanName()).isEqualTo("하트");
    }

    @Test
    void 카드의_랭크_이름을_반환한다() {
        TrumpCard trumpCard = TrumpCard.of(Suit.of("하트"), Rank.of("A"));
        assertThat(trumpCard.getSymbol()).isEqualTo("A");
    }
}