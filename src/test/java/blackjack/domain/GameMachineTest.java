package blackjack.domain;

import blackjack.domain.player.Bet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameMachineTest {

    @Test
    @DisplayName("참가자 이름을 올바르게 작성되어야 한다.")
    public void checkParticipantNames() {
        HashMap<String, Bet> bets = new HashMap<>();
        bets.put("1", new Bet(1000));
        bets.put("2", new Bet(1000));

        Assertions.assertThatThrownBy(() -> new GameMachine(null, bets))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참여자의 이름을 입력해주세요.");
    }

    @Test
    @DisplayName("배팅에는 null이 들어갈 수 없다.")
    public void checkBetsNull() {
        Assertions.assertThatThrownBy(() -> new GameMachine(List.of("1", "2"), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 배팅 입력입니다.");
    }
}
