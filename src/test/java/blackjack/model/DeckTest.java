package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 카드로_덱을_만든다() {
        // given
        Deck deck = new Deck(List.of(
                new NormalCard(2, CardShape.CLOVER),
                new NormalCard(3, CardShape.HEART),
                new AceCard(CardShape.CLOVER),
                new SpecialCard('J', CardShape.CLOVER)
        ));

        // when & then
        assertThat(deck.getCardCount()).isEqualTo(4);
    }
}
