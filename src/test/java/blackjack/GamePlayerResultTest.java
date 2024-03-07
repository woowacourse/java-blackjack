package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.common.Name;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.ResultStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePlayerResultTest {

    @Test
    @DisplayName("이름과 게임 결과를 통해 게임 플레이어의 결과를 생성한다.")
    public void GamePlayerResult_Instance_create_with_name_and_resultStatus() {
        Name name = new Name("초롱");
        ResultStatus resultStatus = ResultStatus.DRAW;

        assertThatCode(() -> {
            new GamePlayerResult(name, resultStatus);
        }).doesNotThrowAnyException();
    }
}
