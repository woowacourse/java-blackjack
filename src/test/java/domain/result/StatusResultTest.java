package domain.result;

import domain.user.Participants;
import domain.user.Playable;
import domain.user.Player;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatusResultTest {
    
    @Test
    @DisplayName("플레이어 게임 결과 테스트")
    void playerGameResultTest() {
        Participants participants = Participants.of("echo,split");
        StatusResult statusResult = new StatusResult();
        statusResult.accumulate(participants.getPlayers().get(0), ResultStatus.WIN);
        Player player = participants.getPlayers().get(0);
        Map<Playable, ResultStatus> resultMap = statusResult.getResultMap();
        Assertions.assertThat(resultMap.get(player)).isEqualTo(ResultStatus.WIN);
        
    }
    
    @Test
    @DisplayName("딜러 게임 결과 테스트")
    void dealerGameResultTest() {
        Participants participants = Participants.of("echo,split");
        StatusResult statusResult = new StatusResult();
        statusResult.accumulate(participants.getPlayers().get(0), ResultStatus.WIN);
        Map<ResultStatus, Integer> dealerResultMap = statusResult.getDealerResult();
        Assertions.assertThat(dealerResultMap.get(ResultStatus.LOSE)).isEqualTo(1);
        Assertions.assertThat(dealerResultMap.get(ResultStatus.DRAW)).isEqualTo(0);
        Assertions.assertThat(dealerResultMap.get(ResultStatus.WIN)).isEqualTo(0);
    }
}
