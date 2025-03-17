package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

class DeckTest {
    
    @Test
    void 카드_덱에서_한_장을_뽑을_수_있다() {
        // given
        final BlackjackDeck deck = new BlackjackDeck();
        
        // expected
        assertThat(deck.draw()).isExactlyInstanceOf(Card.class);
    }
    
    @Test
    void 덱에서_52장_초과의_카드를_뽑으면_예외가_발생한다() {
        // given
        final BlackjackDeck deck = new BlackjackDeck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        
        // expected
        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalStateException.class);
    }
}
