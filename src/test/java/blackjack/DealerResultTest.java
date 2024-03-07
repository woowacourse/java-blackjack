package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.common.Name;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.ResultStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerResultTest {
    @Test
    @DisplayName("딜러 이름과 게임 플레이어 결과 리스트를 통해 딜러 결과를 생성한다.")
    void DealerResult_Instance_create_with_gamePlayerResultList() {
        Name name = new Name("초롱");
        ResultStatus resultStatus = ResultStatus.DRAW;
        GamePlayerResult gamePlayerResult = new GamePlayerResult(name, resultStatus);
        
        assertThatCode(() -> {
            var sut = DealerResult.of(new Name("딜러"), List.of(gamePlayerResult));
            assertThat(sut.getResultWithResultStatus(ResultStatus.DRAW)).isEqualTo(1);
        }).doesNotThrowAnyException();
    }
}
