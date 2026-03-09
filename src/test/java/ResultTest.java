import domain.game.Result;
import domain.game.ResultInfo;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResultTest {

    @Test
    void 플레이어의_승패_판정을_역산하면_딜러의_승패_결과가_나온다() {
        Map<String, ResultInfo> playersResult = new HashMap<>();
        Map<ResultInfo, Integer> expectedDealerResult = new EnumMap<>(ResultInfo.class);

        playersResult.put("user1", ResultInfo.WIN);
        playersResult.put("user2", ResultInfo.WIN);
        playersResult.put("user3", ResultInfo.WIN);
        playersResult.put("user4", ResultInfo.DRAW);
        playersResult.put("user5", ResultInfo.DRAW);
        playersResult.put("user6", ResultInfo.DEFEAT);

        Result result = new Result(playersResult);

        expectedDealerResult.put(ResultInfo.WIN, 1);
        expectedDealerResult.put(ResultInfo.DRAW, 2);
        expectedDealerResult.put(ResultInfo.DEFEAT, 3);

        assertThat(result.getDealerResult()).isEqualTo(expectedDealerResult);

    }
}
