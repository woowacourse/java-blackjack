package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.common.Name;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.Result;
import blackjack.domain.result.ResultStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @Test
    @DisplayName("게임 플레이어 결과 리스트와 딜러 결과를 통해 전체 결과를 생성한다.")
    public void Result_Instance_create_with_gamePlayerResultList_and_dealerResult() {
        Name name = new Name("초롱");
        ResultStatus resultStatus = ResultStatus.DRAW;
        List<GamePlayerResult> gamePlayerResults = List.of(new GamePlayerResult(name, resultStatus));
        DealerResult dealerResult = DealerResult.of(new Name("딜러"), gamePlayerResults);

        assertThatCode(() -> {
            new Result(gamePlayerResults, dealerResult);
        }).doesNotThrowAnyException();
    }
}
