package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.Profit;
import blackjack.domain.player.Name;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @Test
    @DisplayName("플레이어들의 이름과 수익을 통해 전체 결과를 생성한다.")
    public void Result_Instance_create_with_gamePlayerResultList_and_dealerResult() {
        Map<Name, Profit> result = new LinkedHashMap<>();
        Name name = new Name("초롱");
        Profit profit = new Profit(10000);
        result.put(name, profit);

        assertThatCode(() -> {
            new Result(result);
        }).doesNotThrowAnyException();
    }
}
