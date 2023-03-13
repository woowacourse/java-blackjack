package user;

import static testCards.TestCards.QUEEN_DIAMOND;
import static testCards.TestCards.TWO_DIAMOND;

import card.Hand;
import gameState.BettingAmount;
import gameState.Playing;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import playerState.Hittable;
import playerState.Stand;
import playerState.dealerState.UnderSixteen;

class PlayerTest {

    @DisplayName("플레이어는 딜러의 상태인 UnderSixteen을 가질 시 오류를 던진다.")
    @Test
    void canNotHaveUnderSixteen() {
        Assertions.assertThatThrownBy(() -> new Player(
                new PlayerName("echo"),
                UnderSixteen.of(new Playing(new BettingAmount(1000)), new Hand(null)
                )
            )).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어가 가질 수 없는 상태입니다.");
    }

    @DisplayName("플레이어가 게임 중인지 조회한다.(게임 중)")
    @Test
    void isNotFinish() {
        Player player = new Player(
            new PlayerName("echo"),
            Hittable.of(new Playing(new BettingAmount(1000)), new Hand(null))
        );
        Assertions.assertThat(player.isNotFinish()).isTrue();
    }

    @DisplayName("플레이어가 게임 중인지 조회한다.(게임 완료)")
    @Test
    void isFinish() {
        Player player = new Player(
            new PlayerName("echo"),
            Hittable.of(new Playing(new BettingAmount(1000)), new Hand(null))
        );
        player.addCard(QUEEN_DIAMOND);
        player.addCard(null);
        Assertions.assertThat(player.isNotFinish()).isFalse();
    }

    @DisplayName("플레이어의 이름을 조회한다.")
    @Test
    void getName() {
        Player player = new Player(
            new PlayerName("echo"),
            Hittable.of(new Playing(new BettingAmount(1000)), new Hand(null))
        );
        Assertions.assertThat(player.getName()).isEqualTo("echo");
    }

    @DisplayName("플레이어의 모든 카드를 조회한다.")
    @Test
    void getCards() {
        Player player = new Player(
            new PlayerName("echo"),
            Hittable.of(new Playing(new BettingAmount(1000)), new Hand(null))
        );
        player.addCard(QUEEN_DIAMOND);
        Assertions.assertThat(player.getCards()).containsExactly(QUEEN_DIAMOND);
    }

    @DisplayName("딜러의 상태와 비교하여 플레이어의 최종 수익을 조회한다.")
    @Test
    void getFinalBenefit() {
        Player player = new Player(
            new PlayerName("echo"),
            Hittable.of(new Playing(new BettingAmount(1000)), new Hand(null))
        );
        player.addCard(QUEEN_DIAMOND);
        player.addCard(QUEEN_DIAMOND);
        player.addCard(null);
        Stand draw = Stand.of(
            new Playing(new BettingAmount(1000)),
            new Hand(List.of(QUEEN_DIAMOND, QUEEN_DIAMOND))
        );
        Assertions.assertThat(player.getFinalBenefit(draw)).isEqualTo(1000);
    }

    @DisplayName("플레이어의 배팅 금액을 조회한다.")
    @Test
    void getBetAmount() {
        Player player = new Player(
            new PlayerName("echo"),
            Hittable.of(new Playing(new BettingAmount(1000)), new Hand(null))
        );
        Assertions.assertThat(player.getBetAmount()).isEqualTo(1000);
    }

    @DisplayName("플레이어의 점수를 조회한다.")
    @Test
    void getPoint() {
        Player player = new Player(
            new PlayerName("echo"),
            Hittable.of(new Playing(new BettingAmount(1000)), new Hand(null))
        );
        player.addCard(TWO_DIAMOND);
        Assertions.assertThat(player.getPoint()).isEqualTo(2);
    }
}
