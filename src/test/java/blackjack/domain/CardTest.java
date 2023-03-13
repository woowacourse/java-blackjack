package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardTest {

    @Nested
    @DisplayName("카드가 ACE가")
    class isAce {
        @Test
        @DisplayName("맞다면 참이 반환되어야 한다.")
        void isAce_true() {
            // given
            Card card = Card.of(Suit.DIAMOND, Rank.ACE);

            // expect
            assertThat(card.isAce())
                    .isTrue();
        }

        @Test
        @DisplayName("아니면 거짓이 반환되어야 한다.")
        void isAce_false() {
            // given
            Card card = Card.of(Suit.DIAMOND, Rank.TWO);

            // expect
            assertThat(card.isAce())
                    .isFalse();
        }
    }
}
