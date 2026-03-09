import domain.result.Result;
import domain.result.ResultInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ResultTest {

    @Test
    void 딜러의_승패_판정은_플레이어의_승패_판정의_역산이다() {
        List<Integer> expectedDealerScoreBoard = List.of(1, 2, 1);
        Result result = new Result();

        result.setPlayerResult("user1", ResultInfo.WIN);
        result.setPlayerResult("user2", ResultInfo.DRAW);
        result.setPlayerResult("user3", ResultInfo.DRAW);
        result.setPlayerResult("user4", ResultInfo.DEFEAT);

        List<Integer> dealerScoreBoard = result.dealerResult();

        Assertions.assertThat(dealerScoreBoard).containsExactlyElementsOf(expectedDealerScoreBoard);
    }
}
