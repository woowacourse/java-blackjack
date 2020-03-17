package dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseWinningResultDTOTest {
    @DisplayName("승 패 정보가 올바르게 담기는지 테스트")
    @Test
    void getWinningResultTest() {
        Map<String, Double> winningProfit = new LinkedHashMap<>();
        winningProfit.put("pobi", 10000d);
        winningProfit.put("lavine", -10000d);
        winningProfit.put("subway", -10000d);
        ResponseWinningResultDTO responseWinningResultDTO = ResponseWinningResultDTO.create(winningProfit);

        Assertions.assertThat(responseWinningResultDTO.getWinningProfit().keySet()).containsExactly(
                "pobi", "lavine", "subway"
        );
        Assertions.assertThat(responseWinningResultDTO.getWinningProfit().values()).containsExactly(
                10000d, -10000d, -10000d
        );
    }
}
