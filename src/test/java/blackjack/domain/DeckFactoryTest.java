package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Stack;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckFactoryTest {
    
    @Test
    void 덱을_생성한다() {
        final Stack<Card> pack = new Stack<>();
        pack.add(new Card(ACE, SPADE));
        
        final Deck deck = DeckFactory.createWithCount(pack, 1);
        deck.draw();

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class);
    }
}
