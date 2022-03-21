package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.fixture.Fixture.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustTest {

    private static final PlayingCards playingCards = new PlayingCards();

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw_exception() {
        Bust bust = new Bust(playingCards);

        assertThatThrownBy(() -> bust.draw(TEN))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 draw 를 실행할 수 없습니다.");
    }

    @DisplayName("stay 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void stay_exception() {
        Bust bust = new Bust(playingCards);

        assertThatThrownBy(bust::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 stay 를 실행할 수 없습니다.");
    }

    @DisplayName("게임이 진행중인 상태인지 확인한다.")
    @Test
    void is_running() {
        Bust bust = new Bust(playingCards);

        assertThat(bust.isRunning()).isFalse();
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    void is_finished() {
        Bust bust = new Bust(playingCards);

        assertThat(bust.isFinished()).isTrue();
    }

    @DisplayName("블랙잭인지 확인한다.")
    @Test
    void is_blackjack() {
        Bust bust = new Bust(playingCards);

        assertThat(bust.isBlackjack()).isFalse();
    }

    @DisplayName("버스트인지 확인한다.")
    @Test
    void is_bust() {
        Bust bust = new Bust(playingCards);

        assertThat(bust.isBust()).isTrue();
    }

    @DisplayName("카드 총합을 확인한다.")
    @Test
    void card_total() {
        playingCards.add(List.of(TEN, TEN, TEN));
        Bust bust = new Bust(playingCards);

        assertThat(bust.cardTotal()).isEqualTo(30);
    }
}
