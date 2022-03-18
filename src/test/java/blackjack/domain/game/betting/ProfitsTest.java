package blackjack.domain.game.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitsTest {
    @Test
    @DisplayName("참가자들의 수익을 반환한다.")
    void getAllProfits() {
        final Map<String, Integer> playersProfit = new LinkedHashMap<>();
        playersProfit.put("a", 5000);
        playersProfit.put("b", -3000);
        final Profits profits = new Profits(playersProfit);
        assertThat(profits.getAllProfits())
                .containsExactly(entry("딜러", -2000), entry("a", 5000), entry("b", -3000));
    }
}