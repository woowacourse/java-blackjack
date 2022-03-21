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

class BustTest {

    private static final PlayingCards playingCards = new PlayingCards();
    private static final Betting betting = new Betting(1000);

    @DisplayName("bet 을 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void bet_exception() {
        Bust bust = new Bust(playingCards, betting);

        assertThatThrownBy(() -> bust.bet("1000"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Bust 상태일 때는 bet 을 실행할 수 없습니다.");
    }

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw_exception() {
        Bust bust = new Bust(playingCards, betting);

        assertThatThrownBy(() -> bust.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 draw 를 실행할 수 없습니다.");
    }

    @DisplayName("stay 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void stay_exception() {
        Bust bust = new Bust(playingCards, betting);

        assertThatThrownBy(bust::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 stay 를 실행할 수 없습니다.");
    }

    @DisplayName("게임이 진행중인 상태인지 확인한다.")
    @Test
    void is_running() {
        Bust bust = new Bust(playingCards, betting);

        assertThat(bust.isRunning()).isFalse();
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    void is_finished() {
        Bust bust = new Bust(playingCards, betting);

        assertThat(bust.isFinished()).isTrue();
    }

    @DisplayName("패배할 경우 베팅 금액만큼 잃는 것을 확인한다.")
    @Test
    void earning_lose() {
        Bust bust = new Bust(playingCards, betting);
        bust.decideRate(-1);

        assertThat(bust.getEarning()).isEqualTo(-1000.0);
    }

    @DisplayName("무승부일 경우 수익이 없는 것을 확인한다.")
    @Test
    void earning_tie() {
        Bust bust = new Bust(playingCards, betting);
        bust.decideRate(0);

        assertThat(bust.getEarning()).isEqualTo(0.0);
    }

    @DisplayName("승리일 경우 베팅 금액만큼 얻는 것을 확인한다.")
    @Test
    void earning_win() {
        Bust bust = new Bust(playingCards, betting);
        bust.decideRate(1);

        assertThat(bust.getEarning()).isEqualTo(1000.0);
    }

    @DisplayName("카드 총합을 확인한다.")
    @Test
    void card_total() {
        playingCards.add(List.of(
                Card.of(Denomination.KING, Suit.SPADE),
                Card.of(Denomination.KING, Suit.SPADE),
                Card.of(Denomination.KING, Suit.SPADE)));
        Bust bust = new Bust(playingCards, betting);

        assertThat(bust.cardTotal()).isEqualTo(30);
    }
}
