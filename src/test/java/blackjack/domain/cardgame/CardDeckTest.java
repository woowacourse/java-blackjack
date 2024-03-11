package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    @Test
    void 덱에서_카드를_뽑을_수_있다() {
        CardDeck deck = CardDeck.createShuffledDeck();

        Card card = deck.draw();

        assertThat(card).isNotNull();
    }

    @Test
    void 덱에_정확히_52장의_카드가_존재한다() {
        CardDeck deck = CardDeck.createShuffledDeck();

        for (int ignored = 0; ignored < 52; ignored++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 존재하지 않습니다.");
    }
}
