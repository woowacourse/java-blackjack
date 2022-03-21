package blackjack.domain.state;

import blackjack.domain.game.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.fixture.Fixture.ACE;
import static blackjack.fixture.Fixture.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HitTest {

    @DisplayName("draw 를 실행하여 Hit 상태가 되는 것을 확인한다.")
    @Test
    void draw_hit() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(ACE, ACE));
        Hit hit = new Hit(playingCards);

        State state = hit.draw(ACE);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("draw 를 실행하여 Bust 상태가 되는 것을 확인한다.")
    @Test
    void draw_bust() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));
        Hit hit = new Hit(playingCards);

        State state = hit.draw(TEN);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @DisplayName("stay 를 실행하여 Stay 상태가 되는 것을 확인한다.")
    @Test
    void stay() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));
        Hit hit = new Hit(playingCards);

        State state = hit.stay();

        assertThat(state).isInstanceOf(Stay.class);
    }

    @DisplayName("게임이 진행중인 상태인지 확인한다.")
    @Test
    void is_running() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));
        Hit hit = new Hit(playingCards);

        assertThat(hit.isRunning()).isTrue();
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    void is_finished() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));
        Hit hit = new Hit(playingCards);

        assertThat(hit.isFinished()).isFalse();
    }

    @DisplayName("블랙잭인지 확인한다.")
    @Test
    void is_blackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));
        Hit hit = new Hit(playingCards);

        assertThat(hit.isBlackjack()).isFalse();
    }

    @DisplayName("버스트인지 확인한다.")
    @Test
    void is_bust() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));
        Hit hit = new Hit(playingCards);

        assertThat(hit.isBust()).isFalse();
    }

    @DisplayName("수익률을 구할 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void get_earning_rate_exception() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));
        Hit hit = new Hit(playingCards);

        assertThatThrownBy(hit::getEarningRate)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 수익률을 구할 수 없습니다.");
    }

    @DisplayName("수익률을 변경할 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void decide_rate_exception() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));
        Hit hit = new Hit(playingCards);

        assertThatThrownBy(() -> hit.decideRate(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 수익률을 변경할 수 없습니다.");
    }

    @DisplayName("카드 총합을 확인한다.")
    @Test
    void card_total() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));
        Hit hit = new Hit(playingCards);

        assertThat(hit.cardTotal()).isEqualTo(20);
    }
}