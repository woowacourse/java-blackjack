import domain.Result;
import domain.ResultInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ResultTest {

    @Test
    void 딜러의_승패_판정은_플레이어의_승패_판정의_역산이다() {
        List<Integer> expectedDealerScoreBoard=List.of(1,2,1);
        Result result = new Result();

        result.setEntry("user1", ResultInfo.WIN);
        result.setEntry("user2", ResultInfo.DRAW);
        result.setEntry("user3", ResultInfo.DRAW);
        result.setEntry("user4", ResultInfo.DEFEAT);

        List<Integer> dealerScoreBoard=result.dealerResult();

        Assertions.assertThat(dealerScoreBoard).containsExactlyElementsOf(expectedDealerScoreBoard);
    }
}
