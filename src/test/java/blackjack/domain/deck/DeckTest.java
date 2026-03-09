package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void 덱_초기화를_진행하게_되면_기존의_덱과_다르다() {
        Deck deck = new Deck();

        deck.shuffle();
        assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    void 덱_초기화_시_52장의_카드를_가진다() {
        Deck deck = new Deck();

        assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    void 카드를_드로우하면_덱의_카드_수가_1장_줄어든다() {
        Deck deck = new Deck();

        deck.draw();

        assertThat(deck.size()).isEqualTo(51);
    }

    @Test
    void 덱이_비어있을_때_드로우하면_예외가_발생한다() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱에 남은 카드가 없습니다.");
    }

    @Test
    void 마지막_한_장이_남았을_때_드로우하면_덱이_비어있다() {
        Deck deck = new Deck();
        for (int i = 0; i < 51; i++) {
            deck.draw();
        }

        deck.draw();

        assertThat(deck.size()).isEqualTo(0);
    }
}
