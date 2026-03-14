package domain;

import static org.assertj.core.api.Assertions.*;

import infra.FixedCardShuffler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드 덱에서 2장을 드로우 했는지 정상 테스트")
    void drawInitialHand_cards_success() {
        Deck cards = Deck.of(new FixedCardShuffler());

        assertThat(cards.drawInitialHand()).hasSize(2);
    }

    @Test
    @DisplayName("카드 덱에서 1장을 드로우 했는지 정상 테스트")
    void draw_card_success() {
        Deck cards = Deck.of(new FixedCardShuffler());

        assertThat(cards.draw()).isEqualTo(Card.of(Rank.TWO, Suit.SPADE));
    }

}