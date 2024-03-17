package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamersTest {
    @DisplayName("게이머들의 이름 목록을 반환온다.")
    @Test
    void getGamerNames() {
        List<Gamer> gamerList = List.of(
                new Gamer("pobi"),
                new Gamer("jason")
        );
        Gamers gamers = new Gamers(gamerList);

        List<String> gamerNames = gamers.names();

        assertThat(gamerNames).containsExactly("pobi", "jason");
    }

    @DisplayName("게이머들의 점수 목록을 반환한다.")
    @Test
    void getGamerScores() {
        List<Gamer> gamerList = List.of(
                new Gamer("pobi"),
                new Gamer("jason")
        );
        Gamers gamers = new Gamers(gamerList);

        List<Integer> scores = gamers.scores();

        assertThat(scores).containsExactly(0, 0);
    }

    @DisplayName("게이머들의 베팅 금액을 저장한다.")
    @Test
    void saveBettingAmounts() {
        List<Gamer> gamerList = List.of(
                new Gamer("pobi"),
                new Gamer("jason")
        );
        Gamers gamers = new Gamers(gamerList);
        Map<Gamer, Integer> bettingAmounts = new HashMap<>();
        bettingAmounts.put(gamerList.get(0), 10000);
        bettingAmounts.put(gamerList.get(1), 10000);

        assertThatCode(() -> gamers.saveBettingAmounts(bettingAmounts))
                .doesNotThrowAnyException();
    }

    @DisplayName("게이머들의 베팅 금액 목록을 반환한다.")
    @Test
    void getBettingAmounts() {
        List<Gamer> gamerList = List.of(
                new Gamer("pobi"),
                new Gamer("jason")
        );
        Gamers gamers = new Gamers(gamerList);
        Map<Gamer, Integer> bettingAmounts = new HashMap<>();
        bettingAmounts.put(gamerList.get(0), 10000);
        bettingAmounts.put(gamerList.get(1), 10000);
        gamers.saveBettingAmounts(bettingAmounts);

        List<Integer> betAmounts = gamers.betAmounts();

        assertThat(betAmounts).containsExactly(10000, 10000);
    }
}
