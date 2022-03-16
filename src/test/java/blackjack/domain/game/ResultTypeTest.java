package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTypeTest {

    private static final int TYPES_LENGTH = 4;

    @DisplayName("애플리케이션 생성 시점에 BLACKJACK_WIN, WIN, LOSE, DRAW라는 4개의 인스턴스가 생성된다.")
    @Test
    void init() {
        ResultType[] resultTypes = ResultType.values();

        assertThat(resultTypes[0]).isEqualTo(ResultType.valueOf("BLACKJACK_WIN"));
        assertThat(resultTypes[1]).isEqualTo(ResultType.valueOf("WIN"));
        assertThat(resultTypes[2]).isEqualTo(ResultType.valueOf("LOSE"));
        assertThat(resultTypes[3]).isEqualTo(ResultType.valueOf("DRAW"));
        assertThat(resultTypes.length).isEqualTo(TYPES_LENGTH);
    }

    @DisplayName("각 타입에는 플레이어 입장에서의 배당률이 값으로 담겨 있다.")
    @Test
    void getBettingYield() {
        List<ResultType> types = List.of(ResultType.values());

        List<Double> actual = types.stream().map(ResultType::getBettingYield).collect(Collectors.toList());

        assertThat(actual).containsExactly(1.5, 1.0, -1.0, 0.0);
    }
}
