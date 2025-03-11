package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameRecordTest {
    @Test
    void 게임_전적_생성_테스트() {
        // given

        // when
        GameRecord gameRecord = new GameRecord();

        // then
        Assertions.assertThat(gameRecord).isInstanceOf(GameRecord.class);
    }
}
