package blackjack.domain.card;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardTest {

    @Nested
    class isAce_메서드는 {

        @Test
        void 카드가_에이스라면_true_반환한다() {
            final Card card = new Card(ACE, SPADE);

            assertThat(card.isAce()).isTrue();
        }

        @Test
        void 카드가_에이스가_아니라면_false_반환한다() {
            final Card card = new Card(TEN, SPADE);

            assertThat(card.isAce()).isFalse();
        }
    }

    @Test
    void 숫자_점수를_확인한다() {
        final Card card = new Card(ACE, SPADE);

        assertThat(card.getScore()).isEqualTo(1);
    }

    @Test
    void 숫자_이름을_확인한다() {
        final Card card = new Card(ACE, SPADE);

        assertThat(card.getNumberName()).isEqualTo("A");
    }

    @Test
    void 문양_이름을_확인한다() {
        final Card card = new Card(ACE, SPADE);

        assertThat(card.getSuitName()).isEqualTo("스페이드");
    }
}
