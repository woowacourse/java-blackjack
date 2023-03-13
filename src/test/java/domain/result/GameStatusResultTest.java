package domain.result;

import domain.game.GameStatus;
import domain.user.GameMember;
import domain.user.Playable;
import domain.user.Player;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameStatusResultTest {
    
    @Test
    @DisplayName("플레이어 게임 결과 테스트")
    void playerGameResultTest() {
        GameMember gameMember = GameMember.of("echo,split");
        StatusResult statusResult = new StatusResult();
        statusResult.accumulate(gameMember.getPlayers().get(0), GameStatus.WIN);
        Player player = gameMember.getPlayers().get(0);
        Map<Playable, GameStatus> resultMap = statusResult.getResultMap();
        Assertions.assertThat(resultMap.get(player)).isEqualTo(GameStatus.WIN);
        
    }
    
    @Test
    @DisplayName("딜러 게임 결과 테스트")
    void dealerGameResultTest() {
        GameMember gameMember = GameMember.of("echo,split");
        StatusResult statusResult = new StatusResult();
        statusResult.accumulate(gameMember.getPlayers().get(0), GameStatus.WIN);
        Map<GameStatus, Integer> dealerResultMap = statusResult.getDealerResult();
        Assertions.assertThat(dealerResultMap.get(GameStatus.LOSE)).isEqualTo(1);
        Assertions.assertThat(dealerResultMap.get(GameStatus.DRAW)).isEqualTo(0);
        Assertions.assertThat(dealerResultMap.get(GameStatus.WIN)).isEqualTo(0);
    }
}
