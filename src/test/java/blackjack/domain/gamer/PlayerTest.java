package blackjack.domain.gamer;

import blackjack.domain.game.PlayerResult;
import blackjack.domain.money.Chip;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어")
public class PlayerTest {
    @Test
    @DisplayName("플레이어를 생성한다.")
    void createPlayer() {
        // given & when
        Player player = new Player(new Name("lemone"), new Chip(0L));

        // then
        assertThat(player.name()).isEqualTo("lemone");
    }


    @Test
    @DisplayName("플레이어 결과가 Blackjack이면 베팅 금액의 1.5배를 받는다.")
    void playerBlackjackProfit() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(1000L));
        PlayerResult result = PlayerResult.BLACKJACK_WIN;

        // when
        double actual = player.madeProfit(result);

        // then
        assertThat(actual).isEqualTo((long) (1000 * 1.5));
    }

    @Test
    @DisplayName("플레이어 결과가 Win이면 베팅 금액 만큼 받는다.")
    void playerWinProfit() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(1000L));
        PlayerResult result = PlayerResult.WIN;

        // when
        double actual = player.madeProfit(result);

        // then
        assertThat(actual).isEqualTo(1000L);
    }

    @Test
    @DisplayName("플레이어 결과가 Lose이면 베팅 금액을 뺴앗긴다.")
    void playerLoseProfit() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(1000L));
        PlayerResult result = PlayerResult.LOSE;

        // when
        double actual = player.madeProfit(result);

        // then
        assertThat(actual).isEqualTo(-1000L);
    }

    @Test
    @DisplayName("플레이어 결과가 Push이면 수익을 받지 않는다.")
    void playerPushProfit() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(1000L));
        PlayerResult result = PlayerResult.PUSH;

        // when
        double actual = player.madeProfit(result);

        // then
        assertThat(actual).isEqualTo(0);
    }
}
