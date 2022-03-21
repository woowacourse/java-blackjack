package domain.card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("덱에서 카드를 드로우 하는 기능")
    void draw() {
        // given
        Card card_A = Card.getCard(Rank.RANK_A, Suit.HEART);
        Card card_6 = Card.getCard(Rank.RANK_6, Suit.SPADE);

        // when
        Deck deck = new Deck(List.of(card_A, card_6));

        // then
        Assertions.assertThat(deck.draw()).isEqualTo(card_A);
        Assertions.assertThat(deck.draw()).isEqualTo(card_6);
    }
}
