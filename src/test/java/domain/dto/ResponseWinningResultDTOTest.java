package domain.dto;

import dto.ResponseWinningResultDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ResponseWinningResultDTOTest {
    @DisplayName("승 패 정보가 올바르게 담기는지 테스트")
    @Test
    void getWinningResultTest() {
        Map<String, Boolean> winningPlayer = new HashMap<>();
        winningPlayer.put("pobi", true);
        winningPlayer.put("lavine", false);
        winningPlayer.put("subway", false);
        ResponseWinningResultDTO responseWinningResultDTO = ResponseWinningResultDTO.create(winningPlayer);

        Assertions.assertThat(responseWinningResultDTO.getWinningResult()).containsSequence(
                "딜러: 2승 1패", "pobi: 승", "subway: 패", "lavine: 패"
        );
    }
}
