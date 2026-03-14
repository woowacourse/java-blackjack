package domain;

import static org.assertj.core.api.Assertions.*;

import infra.FixedCardShuffler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("카드 덱에서 2장이 드로우 됐는지 정상 테스트")
    void drawInitialHand__success() {
        Deck deck = Deck.of(new FixedCardShuffler());

        assertThat(deck.drawInitialHand()).hasSize(2);
    }

    @Test
    @DisplayName("카드를 한 장 뽑으면 덱의 첫 번째 카드를 반환한다")
    void draw_card_success() {
        Deck orderDeck = Deck.of(new FixedCardShuffler()); // 첫 카드: 2 SPADE

        assertThat(orderDeck.draw()).isEqualTo(Card.of(Rank.TWO, Suit.SPADE));
    }

}