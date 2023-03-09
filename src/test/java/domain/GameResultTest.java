package domain;

import domain.game.GameResult;
import domain.game.GameResult.Result;
import domain.user.Participants;
import domain.user.Player;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {
    
    @Test
    @DisplayName("플레이어 게임 결과 테스트")
    void playerGameResultTest() {
        Participants participants = Participants.of("echo,split");
        GameResult gameResult = new GameResult();
        gameResult.accumulate(participants.getPlayers().get(0), Result.WIN);
        Player player = participants.getPlayers().get(0);
        HashMap<String, Result> resultMap = gameResult.getResultMap();
        Assertions.assertThat(resultMap.get(player.getName())).isEqualTo(Result.WIN);
        
    }
    
    @Test
    @DisplayName("딜러 게임 결과 테스트")
    void dealerGameResultTest() {
        Participants participants = Participants.of("echo,split");
        GameResult gameResult = new GameResult();
        gameResult.accumulate(participants.getPlayers().get(0), Result.WIN);
        HashMap<Result, Integer> dealerResultMap = gameResult.generateDealerResult();
        Assertions.assertThat(dealerResultMap.get(Result.LOSE)).isEqualTo(1);
        Assertions.assertThat(dealerResultMap.get(Result.DRAW)).isEqualTo(0);
        Assertions.assertThat(dealerResultMap.get(Result.WIN)).isEqualTo(0);
    }
}
