package domain.user;

import domain.Betting;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @DisplayName("플레이어는 배팅을 할 수 있다.")
    @Test
    void
    test3() {
        //given
        Map<User, Long> bettingMap = new HashMap<>();
        long money = 30000;
        User player = new Player("Lemon");

        bettingMap.put(player, money);
        //when
        Betting betting = new Betting(bettingMap);

        //then
        Assertions.assertThat(betting.getBetting().get(player)).isEqualTo(30000);
    }
}
