package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardTest {

    @Test
    void 카드는_숫자를_가진다() {
        // given
        Card card = new Card(1, "다이아몬드");

        // when
        int number = card.getNumber();

        // then
        assertThat(number).isEqualTo(1);
    }

    @Test
    void 카드는_모양을_가진다() {
        // given
        Card card = new Card(1, "다이아몬드");

        // when
        String shape = card.getShape();

        // then
        assertThat(shape).isEqualTo("다이아몬드");
    }

    @Nested
    class 카드의_숫자가 {
        @Test
        void _1미만이면_예외() {
            // then
            assertThatThrownBy(() -> new Card(0, "다이아몬드"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void _10초과면_예외() {
            // then
            assertThatThrownBy(() -> new Card(11, "다이아몬드"))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 카드의_모양이 {
        @Test
        void _아닐_경우_예외() {
            // then
            assertThatThrownBy(() -> new Card(1, "코끼리"))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

}
