package object;

import object.game.BattleResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class BattleResultTest {

    @ParameterizedTest
    @EnumSource(BattleResult.class)
    void 배틀_결과_생성_테스트(BattleResult battleResult) {
        Assertions.assertThat(battleResult).isInstanceOf(BattleResult.class);
    }
}
