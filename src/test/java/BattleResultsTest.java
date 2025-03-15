import ScoreResult.BattleResult;
import ScoreResult.BattleResults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BattleResultsTest {

    @Test
    void 결과_추가_확인() {
        //given
        BattleResult battleResult = BattleResult.WIN;
        BattleResults battleResults = new BattleResults();

        //when
        battleResults.addResult(battleResult);

        //then
        Assertions.assertThat(battleResults.getResults().containsKey(battleResult)).isTrue();
    }
}