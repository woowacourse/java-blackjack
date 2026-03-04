package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    // 성공 케이스
    @Test
    @DisplayName("덱 초기화 시 52장의 카드를 가진다")
    void 덱_초기화_시_52장의_카드를_가진다() {
        Deck deck = new Deck();

        assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드를 드로우하면 카드 한 장을 반환한다")
    void 카드를_드로우하면_카드_한_장을_반환한다() {
        Deck deck = new Deck();

        Card drawn = deck.draw();

        assertThat(drawn).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드를 드로우하면 덱의 카드 수가 1장 줄어든다")
    void 카드를_드로우하면_덱의_카드_수가_1장_줄어든다() {
        Deck deck = new Deck();

        deck.draw();

        assertThat(deck.size()).isEqualTo(51);
    }

    // 실패 케이스
    @Test
    @DisplayName("덱이 비어있을 때 드로우하면 예외가 발생한다")
    void 덱이_비어있을_때_드로우하면_예외가_발생한다() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱에 남은 카드가 없습니다.");
    }

    // 경계값 - 마지막 1장
    @Test
    @DisplayName("마지막 한 장이 남았을 때 드로우에 성공한다")
    void 마지막_한_장이_남았을_때_드로우에_성공한다() {
        Deck deck = new Deck();
        for (int i = 0; i < 51; i++) {
            deck.draw();
        }

        Card lastCard = deck.draw();

        assertThat(lastCard).isInstanceOf(Card.class);
    }
}
