package blackjack.gametable.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void 덱_생성_시_사이즈가_52임을_확인한다() {
        // when
        Deck deck = Deck.initialize();

        // then
        assertThat(deck.getSize()).isEqualTo(52);
    }

    @Test
    void 카드_한_장을_뽑을_시_카드_숫자의_범위를_확인한다() {
        // given
        Deck deck = Deck.initialize();

        // when
        Card card = deck.drawCard();

        // then
        assertThat(card.getRankScore()).isBetween(1, 11);
    }

    @Test
    void 초기_카드를_뽑는다() {
        // given
        Deck deck = Deck.initialize();

        // when
        Cards cards = deck.drawInitialCards();

        // then
        assertThat(cards.getSize()).isEqualTo(2);
    }

    @Test
    void 플레이어_수만큼_초기_카드를_뽑는다() {
        // given
        Deck deck = Deck.initialize();

        // when
        List<Cards> cards = deck.drawInitialCards(3);

        // then
        assertThat(cards).hasSize(3);
        assertThat(cards.getFirst().getSize()).isEqualTo(2);
    }

}
