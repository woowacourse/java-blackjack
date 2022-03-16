package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerBetTest {

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        PlayersBet playerBet = new PlayersBet();
        assertThat(playerBet).isNotNull();
    }

    @Test
    @DisplayName("플레이어와 배팅금 추가 테스트")
    void testAdd() {
        PlayersBet playerBet = new PlayersBet();
        Player player = Player.of("pobi");
        Money money = new Money(10000);

        playerBet.add(player, money);

        assertThat(playerBet.get(player)).isEqualTo(money);
    }
}
