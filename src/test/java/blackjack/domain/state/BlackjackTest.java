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

class BlackjackTest {

    private static final PlayingCards playingCards = new PlayingCards();
    private static final Betting betting = new Betting(1000);

    @DisplayName("bet 을 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void bet_exception() {
        Blackjack blackjack = new Blackjack(playingCards, betting);

        assertThatThrownBy(() -> blackjack.bet("1000"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Blackjack 상태일 때는 bet 을 실행할 수 없습니다.");
    }

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw_exception() {
        Blackjack blackjack = new Blackjack(playingCards, betting);

        assertThatThrownBy(() -> blackjack.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 draw 를 실행할 수 없습니다.");
    }

    @DisplayName("stay 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void stay_exception() {
        Blackjack blackjack = new Blackjack(playingCards, betting);

        assertThatThrownBy(blackjack::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 stay 를 실행할 수 없습니다.");
    }

    @DisplayName("게임이 진행중인 상태인지 확인한다.")
    @Test
    void is_running() {
        Blackjack blackjack = new Blackjack(playingCards, betting);

        assertThat(blackjack.isRunning()).isFalse();
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    void is_finished() {
        Blackjack blackjack = new Blackjack(playingCards, betting);

        assertThat(blackjack.isFinished()).isTrue();
    }

    @DisplayName("딜러가 블랙잭이어서 패배할 경우 베팅 금액만큼 잃는 것을 확인한다.")
    @Test
    void earning_lose() {
        Blackjack blackjack = new Blackjack(playingCards, betting);
        blackjack.decideRate(-1);

        assertThat(blackjack.getEarning()).isEqualTo(-1000.0);
    }

    @DisplayName("딜러와 플레이어 모두 블랙잭이어서 무승부일 경우 수익이 없는 것을 확인한다.")
    @Test
    void earning_tie() {
        Blackjack blackjack = new Blackjack(playingCards, betting);
        blackjack.decideRate(0);

        assertThat(blackjack.getEarning()).isEqualTo(0.0);
    }

    @DisplayName("플레이어가 블랙잭이어서 우승할 경우 베팅 금액의 1.5 배를 얻는 것을 확인한다.")
    @Test
    void earning_win() {
        Blackjack blackjack = new Blackjack(playingCards, betting);
        blackjack.decideRate(1.5);

        assertThat(blackjack.getEarning()).isEqualTo(1500.0);
    }

    @DisplayName("카드 총합을 확인한다.")
    @Test
    void card_total() {
        playingCards.add(List.of(
                Card.of(Denomination.KING, Suit.SPADE),
                Card.of(Denomination.KING, Suit.SPADE)));
        Blackjack blackjack = new Blackjack(playingCards, betting);

        assertThat(blackjack.cardTotal()).isEqualTo(20);
    }
}