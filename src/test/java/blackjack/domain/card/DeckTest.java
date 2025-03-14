package blackjack.domain.card;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void 덱에는_52장의_카드가_존재한다() {
        // given
        Deck deck = new Deck();

        // when
        assertThat(deck.getCards()).hasSize(52);

    }

    @Test
    void 중복되는_카드가_존재하면_예외를_던진다() {
        // given
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();

        // when & then
        assertThat(cards).doesNotHaveDuplicates();
    }

    @Nested
    class 카드뽑기 {

        @Test
        void 카드가_소진되면_예외를_던진다() {
            Deck deck = new Deck();

            for (int i = 0; i < 52; i++) {
                deck.takeSingleCard();
            }

            assertThatThrownBy(deck::takeSingleCard)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("모든 카드를 소진하였습니다.");
        }
    }
}
