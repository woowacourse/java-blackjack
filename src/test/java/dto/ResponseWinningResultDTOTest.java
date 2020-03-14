package dto;

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

        Assertions.assertThat(responseWinningResultDTO.getWinningPlayer().keySet()).containsSequence(
                "pobi", "subway", "lavine"
        );
        Assertions.assertThat(responseWinningResultDTO.getWinningPlayer().values()).containsSequence(
                true, false, false
        );
    }
}
