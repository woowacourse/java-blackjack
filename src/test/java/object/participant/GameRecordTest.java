package object.participant;

import java.util.Map;
import object.game.BattleResult;
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
    @EnumSource(BattleResult.class)
    void 게임_전적_업데이트_테스트(BattleResult battleResult) {
        // given
        GameRecord gameRecord = new GameRecord();

        // when
        gameRecord.add(battleResult);

        // then
        Map<BattleResult, Integer> record = gameRecord.getGameRecord();
        Assertions.assertThat(record.getOrDefault(battleResult, 0)).isEqualTo(1);
    }
}
