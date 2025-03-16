package object.participant;

import java.util.Map;
import object.game.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class GameRecordTest {
    @Test
    void 게임_전적_생성_테스트() {
        // given

        // when
        GameRecord gameRecord = new GameRecord();

        // then
        Assertions.assertThat(gameRecord).isInstanceOf(GameRecord.class);
    }

    @ParameterizedTest
    @EnumSource(GameResult.class)
    void 게임_전적_업데이트_테스트(GameResult gameResult) {
        // given
        GameRecord gameRecord = new GameRecord();

        // when
        gameRecord.add(gameResult);

        // then
        Map<GameResult, Integer> record = gameRecord.getGameRecord();
        Assertions.assertThat(record.getOrDefault(gameResult, 0)).isEqualTo(1);
    }
}
