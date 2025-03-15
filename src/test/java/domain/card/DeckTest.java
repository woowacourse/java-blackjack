package domain.card;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.TWO_HEART;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void 덱의_맨_뒤부터_카드를_뽑는다() {
        // given
        Card card1 = ACE_HEART;
        Card card2 = TWO_HEART;
        Deck deck = new Deck(List.of(card1, card2));

        // when
        final Card drawn = deck.drawCard();

        // when & then
        Assertions.assertThat(drawn).isEqualTo(card2);
    }

    @Test
    void 덱에_카드가_없는데_드로우하면_예외가_발생한다() {
        // given
        Deck deck = new Deck(List.of());

        // when & then
        Assertions.assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class);
    }
}