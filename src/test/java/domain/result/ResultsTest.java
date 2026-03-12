package domain.result;

import static domain.WinningStatus.WIN;
import static util.TestUtil.createResult;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResultsTest {

    @Test
    void addResult() {
    }

    @Test
    void results() {
    }


    @Test
    void 매개변수_없는_생성자로_생성이_가능하다() {
        // given
        Results results = new Results();

        // when, then
        Assertions.assertNotNull(results);
    }

    @Test
    void 베팅_리스트로_생성이_가능하다() {
        // given
        Results results = new Results(List.of(createResult("봉구스", WIN), createResult("시오", WIN)));

        // when, then
        Assertions.assertEquals(2, results.results().size());
    }

    @Test
    void 베팅을_추가할_수_있다() {
        // given
        Results results = new Results();

        // when
        Results newResults = results.addResult(createResult("봉구스", WIN));

        // then
        Assertions.assertEquals(1, newResults.results().size());
    }
}