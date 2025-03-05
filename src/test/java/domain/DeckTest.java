package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 덱에서_카드를_한_장_뽑는다() {
        // given
        final int cardCount = 1;

        // when
        Deck deck = DeckGenerator.generateDeck();
        List<Card> card = deck.drawCards(cardCount);

        // then
        Assertions.assertThat(card.size()).isEqualTo(cardCount);
    }

    @Test
    void 덱에서_카드를_두_장_뽑는다() {
        // given
        final int cardCount = 2;

        // when
        Deck deck = DeckGenerator.generateDeck();
        List<Card> card = deck.drawCards(cardCount);

        // then
        Assertions.assertThat(card.size()).isEqualTo(cardCount);
    }
}
