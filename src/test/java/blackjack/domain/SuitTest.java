package blackjack.domain;

import static blackjack.domain.Suit.CLOVER;
import static blackjack.domain.Suit.DIAMOND;
import static blackjack.domain.Suit.HEART;
import static blackjack.domain.Suit.SPADE;
import static blackjack.domain.Suit.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SuitTest {

    @Test
    void 스페이드_하트_다이아몬드_클로버를_제외한_다른_문자가_들어오면_예외_처리한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            of("네모");
        });
    }

    @Test
    void 하트가_입력되면_하트가_반환된다() {
        String input = "하트";
        Suit heart = of(input);
        assertThat(heart).isEqualTo(HEART);
    }

    @Test
    void 스페이드가_입력되면_스페이드가_반환된다() {
        String input = "스페이드";
        Suit spade = of(input);
        assertThat(spade).isEqualTo(SPADE);
    }

    @Test
    void 다이아몬드가_입력되면_다이아몬드가_반환된다() {
        String input = "다이아몬드";
        Suit diamond = of(input);
        assertThat(diamond).isEqualTo(DIAMOND);
    }

    @Test
    void 클로버가_입력되면_클로버가_반환된다() {
        String input = "클로버";
        Suit clover = of(input);
        assertThat(clover).isEqualTo(CLOVER);
    }
}
