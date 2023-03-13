package playerState;

import static testCards.TestCards.ACE_DIAMOND;
import static testCards.TestCards.JACK_DIAMOND;
import static testCards.TestCards.NINE_DIAMOND;
import static testCards.TestCards.QUEEN_DIAMOND;
import static testCards.TestCards.SEVEN_DIAMOND;
import static testCards.TestCards.TEN_DIAMOND;
import static testCards.TestCards.THREE_DIAMOND;
import static testCards.TestCards.TWO_DIAMOND;

import card.Hand;
import gameState.BettingAmount;
import gameState.Playing;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HittableTest {

    @DisplayName("카드를 더 뽑을 수 없는 상태로 생성시 오류를 던진다.")
    @Test
    void notHittable() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, ACE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Assertions.assertThatThrownBy(() -> Hittable.of(playing, hand))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("카드를 더 뽑을 수 있는 상태가 아닙니다.");
    }

    @DisplayName("Hit 가능한 상태에서 카드를 지급하여 총점이 21점이 안되는 경우 Hit를 반환한다.")
    @Test
    void hitBecomeHit() {
        Hand hand = new Hand(List.of(JACK_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Hittable hittable = Hittable.of(playing, hand);
        PlayerStatus playerStatus = hittable.addCard(NINE_DIAMOND);
        Assertions.assertThat(playerStatus).isInstanceOf(Hittable.class);
    }

    @DisplayName("Hit 가능한 상태에서 카드를 지급하여 총점 21점에 카드가 2장이 되는 경우 블랙잭을 반환한다.")
    @Test
    void hitBecomeBlackJack() {
        Hand hand = new Hand(List.of(JACK_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Hittable hittable = Hittable.of(playing, hand);
        PlayerStatus playerStatus = hittable.addCard(ACE_DIAMOND);
        Assertions.assertThat(playerStatus).isInstanceOf(BlackJack.class);
    }

    @DisplayName("Hit 가능한 상태에서 카드를 지급하여 총점 21점에 카드가 3장이 되는 경우 Stand를 반환한다.")
    @Test
    void hitBecomeStand() {
        Hand hand = new Hand(List.of(THREE_DIAMOND, SEVEN_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Hittable hittable = Hittable.of(playing, hand);
        PlayerStatus playerStatus = hittable.addCard(ACE_DIAMOND);
        Assertions.assertThat(playerStatus).isInstanceOf(Stand.class);
    }

    @DisplayName("Hit 가능한 상태에서 카드를 지급하여 총점이 21점이 넘는 경우 Bust를 반환한다.")
    @Test
    void hitBecomeBust() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, QUEEN_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Hittable hittable = Hittable.of(playing, hand);
        PlayerStatus playerStatus = hittable.addCard(TWO_DIAMOND);
        Assertions.assertThat(playerStatus).isInstanceOf(Bust.class);
    }

    @DisplayName("Hit 가능한 상태에서 최종 상태를 구할 경우 오류를 던진다.")
    @Test
    void whenHittable() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, QUEEN_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Hittable playerState = Hittable.of(playing, hand);
        PlayerStatus dealerState = playerState.addCard(TWO_DIAMOND);
        Assertions.assertThatThrownBy(() -> playerState.getFinalState(dealerState))
            .isExactlyInstanceOf(IllegalStateException.class)
            .hasMessage("아직 게임 중 입니다.");
    }
}
