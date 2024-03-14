package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.Profit;
import blackjack.domain.player.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePlayerResultTest {

    @Test
    @DisplayName("이름과 수익을 통해 게임 플레이어의 결과를 생성한다.")
    public void GamePlayerResult_Instance_create_with_name_and_profit() {
        Name name = new Name("초롱");
        Profit profit = new Profit(10000);

        assertThatCode(() -> {
            new GamePlayerResult(name, profit);
        }).doesNotThrowAnyException();
    }
}
