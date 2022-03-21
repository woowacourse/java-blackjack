package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HitTest {

    private static final Betting betting = new Betting(1000);

    @DisplayName("bet 을 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void bet() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(Card.of(Denomination.ACE, Suit.SPADE), Card.of(Denomination.ACE, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        assertThatThrownBy(() -> hit.bet("1000"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Hit 상태일 때는 bet 을 실행할 수 없습니다.");
    }

    @DisplayName("draw 를 실행하여 Hit 상태가 되는 것을 확인한다.")
    @Test
    void draw_hit() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(Card.of(Denomination.ACE, Suit.SPADE), Card.of(Denomination.ACE, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        State state = hit.draw(Card.of(Denomination.ACE, Suit.SPADE));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("draw 를 실행하여 Bust 상태가 되는 것을 확인한다.")
    @Test
    void draw_bust() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(Card.of(Denomination.KING, Suit.SPADE), Card.of(Denomination.KING, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        State state = hit.draw(Card.of(Denomination.KING, Suit.SPADE));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @DisplayName("stay 를 실행하여 Stay 상태가 되는 것을 확인한다.")
    @Test
    void stay() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(Card.of(Denomination.KING, Suit.SPADE), Card.of(Denomination.KING, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        State state = hit.stay();

        assertThat(state).isInstanceOf(Stay.class);
    }

    @DisplayName("게임이 진행중인 상태인지 확인한다.")
    @Test
    void is_running() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(Card.of(Denomination.KING, Suit.SPADE), Card.of(Denomination.KING, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        assertThat(hit.isRunning()).isTrue();
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    void is_finished() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(Card.of(Denomination.KING, Suit.SPADE), Card.of(Denomination.KING, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        assertThat(hit.isFinished()).isFalse();
    }

    @DisplayName("수익률을 변경할 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void decide_rate() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(Card.of(Denomination.KING, Suit.SPADE), Card.of(Denomination.KING, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        assertThatThrownBy(() -> hit.decideRate(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 수익률을 변경할 수 없습니다.");
    }

    @DisplayName("수익 계산 시 예외가 발생하는 것을 확인한다.")
    @Test
    void earning_exception() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(Card.of(Denomination.KING, Suit.SPADE), Card.of(Denomination.KING, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        assertThatThrownBy(hit::getEarning)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 수익을 계산할 수 없습니다.");
    }

    @DisplayName("카드 총합을 확인한다.")
    @Test
    void card_total() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(
                Card.of(Denomination.KING, Suit.SPADE),
                Card.of(Denomination.KING, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        assertThat(hit.cardTotal()).isEqualTo(20);
    }
}