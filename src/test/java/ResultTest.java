import domain.game.Result;
import domain.game.ResultInfo;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResultTest {

    @Test
    void 플레이어의_승패_판정을_역산하면_딜러의_승패_결과가_나온다() {
        Map<ResultInfo, Integer> expectedDealerResult = new EnumMap<>(ResultInfo.class);

        Result result = new Result();
        result.setPlayerResult("user1", ResultInfo.WIN);
        result.setPlayerResult("user2", ResultInfo.WIN);
        result.setPlayerResult("user3", ResultInfo.WIN);
        result.setPlayerResult("user4", ResultInfo.DRAW);
        result.setPlayerResult("user5", ResultInfo.DRAW);
        result.setPlayerResult("user6", ResultInfo.DEFEAT);

        result.setDealerResult(result.getPlayersResult());
        expectedDealerResult.put(ResultInfo.WIN, 1);
        expectedDealerResult.put(ResultInfo.DRAW, 2);
        expectedDealerResult.put(ResultInfo.DEFEAT, 3);

        assertThat(result.getDealerResult()).isEqualTo(expectedDealerResult);

    }
}
