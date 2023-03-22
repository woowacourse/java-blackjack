package playerState.dealerState;

import static testCards.TestCards.ACE_DIAMOND;
import static testCards.TestCards.EIGHT_DIAMOND;
import static testCards.TestCards.JACK_DIAMOND;
import static testCards.TestCards.NINE_DIAMOND;
import static testCards.TestCards.QUEEN_DIAMOND;
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
import playerState.BlackJack;
import playerState.Bust;
import playerState.PlayerStatus;
import playerState.Stand;

class UnderSixteenTest {

    @DisplayName("총점이 16이 넘는 카드로 생성할 경우 오류를 던진다.")
    @Test
    void notBlackJackPoint() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, NINE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Assertions.assertThatThrownBy(() -> UnderSixteen.of(playing, hand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("카드의 총합이 16이 넘습니다.");
    }

    @DisplayName("딜러가 16이하인 상태에서 최종 결과를 조회시 오류를 던진다.")
    @Test
    void canNotGetFinalState() {
        Hand hand = new Hand(List.of(JACK_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        UnderSixteen underSixteen = UnderSixteen.of(playing, hand);
        Assertions.assertThatThrownBy(() -> underSixteen.getFinalState(underSixteen))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("딜러는 16점이 넘을 때 까지 카드를 받아야 합니다.");
    }

    @DisplayName("카드를 추가로 지급해도 16이하인 경우 UnderSixteen을 반환한다.")
    @Test
    void stillUnderSixteen() {
        Hand hand = new Hand(List.of(JACK_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        UnderSixteen underSixteen = UnderSixteen.of(playing, hand);
        PlayerStatus playerStatus = underSixteen.addCard(TWO_DIAMOND);
        Assertions.assertThat(playerStatus).isInstanceOf(UnderSixteen.class);
    }

    @DisplayName("카드가 2장이고 21점일 경우 BlackJack를 반환한다.")
    @Test
    void becomeBlackJack() {
        Hand hand = new Hand(List.of(JACK_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        UnderSixteen underSixteen = UnderSixteen.of(playing, hand);
        PlayerStatus playerStatus = underSixteen.addCard(ACE_DIAMOND);
        Assertions.assertThat(playerStatus).isInstanceOf(BlackJack.class);
    }

    @DisplayName("카드가 2장보다 많고 21점일 경우 Stand를 반환한다.")
    @Test
    void becomeTwentyOnePointStand() {
        Hand hand = new Hand(List.of(JACK_DIAMOND, THREE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        UnderSixteen underSixteen = UnderSixteen.of(playing, hand);
        PlayerStatus playerStatus = underSixteen.addCard(EIGHT_DIAMOND);
        Assertions.assertThat(playerStatus).isInstanceOf(Stand.class);
    }

    @DisplayName("카드를 지급하여 17이상 21미만일 경우 Stand를 반환한다.")
    @Test
    void becomeStand() {
        Hand hand = new Hand(List.of(JACK_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        UnderSixteen underSixteen = UnderSixteen.of(playing, hand);
        PlayerStatus playerStatus = underSixteen.addCard(NINE_DIAMOND);
        Assertions.assertThat(playerStatus).isInstanceOf(Stand.class);
    }

    @DisplayName("카드를 지급하여 21점이 넘을 경우 Bust를 반환한다.")
    @Test
    void becomeBust() {
        Hand hand = new Hand(List.of(JACK_DIAMOND, THREE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        UnderSixteen underSixteen = UnderSixteen.of(playing, hand);
        PlayerStatus playerStatus = underSixteen.addCard(QUEEN_DIAMOND);
        Assertions.assertThat(playerStatus).isInstanceOf(Bust.class);
    }
}
