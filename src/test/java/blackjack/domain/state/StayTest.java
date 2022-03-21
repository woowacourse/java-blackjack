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
import static org.junit.jupiter.api.Assertions.*;

class StayTest {

    private static final PlayingCards playingCards = new PlayingCards();
    private static final Betting betting = new Betting(1000);

    @DisplayName("bet 을 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void bet() {
        Stay stay = new Stay(playingCards, betting);

        assertThatThrownBy(() -> stay.bet("1000"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Stay 상태일 때는 bet 을 실행할 수 없습니다.");
    }

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw() {
        Stay stay = new Stay(playingCards, betting);

        assertThatThrownBy(() -> stay.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 draw 를 실행할 수 없습니다.");
    }

    @DisplayName("stay 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void stay() {
        Stay stay = new Stay(playingCards, betting);

        assertThatThrownBy(stay::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 stay 를 실행할 수 없습니다.");
    }

    @DisplayName("게임이 진행중인 상태인지 확인한다.")
    @Test
    void is_running() {
        Stay stay = new Stay(playingCards, betting);

        assertThat(stay.isRunning()).isFalse();
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    void is_finished() {
        Stay stay = new Stay(playingCards, betting);

        assertThat(stay.isFinished()).isTrue();
    }

    @DisplayName("플레이어가 버스트여서 패배할 경우 베팅 금액만큼 잃는 것을 확인한다.")
    @Test
    void earning_player_bust() {
        Stay stay = new Stay(playingCards, betting);
        stay.decideRate(-1);

        assertThat(stay.earning()).isEqualTo(-1000.0);
    }

    @DisplayName("딜러가 버스트여서 승리할 경우 베팅 금액만큼 얻는 것을 확인한다.")
    @Test
    void earning_dealer_bust() {
        Stay stay = new Stay(playingCards, betting);
        stay.decideRate(1);

        assertThat(stay.earning()).isEqualTo(1000.0);
    }

    @DisplayName("딜러보다 카드 총 합이 작아 패배할 경우 베팅 금액만큼 잃는 것을 확인한다.")
    @Test
    void earning_lose() {
        Stay stay = new Stay(playingCards, betting);
        stay.decideRate(-1);

        assertThat(stay.earning()).isEqualTo(-1000.0);
    }

    @DisplayName("딜러와 플레이어의 카드 총 합이 같아 무승부일 경우 수익이 없는 것을 확인한다.")
    @Test
    void earning_tie() {
        Stay stay = new Stay(playingCards, betting);
        stay.decideRate(0);

        assertThat(stay.earning()).isEqualTo(0.0);
    }

    @DisplayName("딜러보다 카드 총 합이 커서 승리할 경우 베팅 금액만큼 얻는 것을 확인한다.")
    @Test
    void earning_win() {
        Blackjack blackjack = new Blackjack(playingCards, betting);
        blackjack.decideRate(1);

        assertThat(blackjack.earning()).isEqualTo(1000.0);
    }

    @DisplayName("카드 총합을 확인한다.")
    @Test
    void card_total() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(
                Card.of(Denomination.KING, Suit.SPADE),
                Card.of(Denomination.KING, Suit.SPADE)));
        Stay stay = new Stay(playingCards, betting);

        assertThat(stay.cardTotal()).isEqualTo(20);
    }
}
