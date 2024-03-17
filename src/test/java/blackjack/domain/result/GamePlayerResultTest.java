package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.player.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePlayerResultTest {

    @Test
    @DisplayName("이름과 최종 결과 금액을 통해 게임 플레이어의 결과를 생성한다.")
    void create_with_name_and_resultStatus() {
        final Name name = new Name("초롱");
        final var prize = new PrizeMoney(1000);


        assertThatCode(() -> {
            new GamePlayerResult(name, prize);
        }).doesNotThrowAnyException();
    }
}
