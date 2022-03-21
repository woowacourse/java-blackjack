package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StayTest {

    private static final PlayingCards playingCards = new PlayingCards();

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw_exception() {
        Stay stay = new Stay(playingCards);

        assertThatThrownBy(() -> stay.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 draw 를 실행할 수 없습니다.");
    }

    @DisplayName("stay 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void stay_exception() {
        Stay stay = new Stay(playingCards);

        assertThatThrownBy(stay::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 stay 를 실행할 수 없습니다.");
    }

    @DisplayName("게임이 진행중인 상태인지 확인한다.")
    @Test
    void is_running() {
        Stay stay = new Stay(playingCards);

        assertThat(stay.isRunning()).isFalse();
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    void is_finished() {
        Stay stay = new Stay(playingCards);

        assertThat(stay.isFinished()).isTrue();
    }

    @DisplayName("블랙잭인지 확인한다.")
    @Test
    void is_blackjack() {
        Stay stay = new Stay(playingCards);

        assertThat(stay.isBlackjack()).isFalse();
    }

    @DisplayName("버스트인지 확인한다.")
    @Test
    void is_bust() {
        Stay stay = new Stay(playingCards);

        assertThat(stay.isBust()).isFalse();
    }

    @DisplayName("카드 총합을 확인한다.")
    @Test
    void card_total() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(
                Card.of(Denomination.KING, Suit.SPADE),
                Card.of(Denomination.KING, Suit.SPADE)));
        Stay stay = new Stay(playingCards);

        assertThat(stay.cardTotal()).isEqualTo(20);
    }
}
