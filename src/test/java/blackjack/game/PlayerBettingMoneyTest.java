package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBettingMoneyTest {

    private PlayerBettingMoney playerBettingMoney;
    private Player player;
    private Money money;

    @BeforeEach
    void beforeEach() {
        playerBettingMoney = new PlayerBettingMoney();
        player = new Player("atto");
        money = new Money(10000);

        playerBettingMoney.addBetting(player, money);
    }

    @Test
    @DisplayName("플레이어별 배팅 금액을 저장한다.")
    void addBettingTest() {
        assertThat(playerBettingMoney.getBettingAmountOf(player)).isEqualTo(money);
    }

    @Test
    @DisplayName("존재하지 않는 플레이어의 배팅 결과를 가져오려 할 경우 예외를 발생시킨다.")
    void notExistPlayerTest() {
        Player notExistPlayer = new Player("pobi");

        assertThatThrownBy(() -> playerBettingMoney.getBettingAmountOf(notExistPlayer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 플레이어입니다.");
    }

    @Test
    @DisplayName("이미 존재하는 플레이어의 배팅 결과를 저장하려 할 경우 예외를 발생시킨다.")
    void alreadyExistPlayerTest() {
        assertThatThrownBy(() -> playerBettingMoney.addBetting(player, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이미 존재하는 플레이어입니다.");
    }
}
