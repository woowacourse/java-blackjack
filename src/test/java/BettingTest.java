import domain.Betting;
import domain.user.Player;
import domain.user.User;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @DisplayName("플레이어는 배팅을 할 수 있다.")
    @Test
    void
    test1() {
        //given
        Map<User, Long> bettingMap = new HashMap<>();
        long submitMoney = 3000000L;
        User user = new Player("Lemon");
        bettingMap.put(user, 0L);
        Betting betting = new Betting(bettingMap);

        //when
        betting.summitBet(user, submitMoney);

        //then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(betting.getBetting()).containsKey(user);
            softAssertions.assertThat(betting.getBetting().get(user)).isEqualTo(3000000L);
        });
    }

}
