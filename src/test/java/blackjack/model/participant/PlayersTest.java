package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어들 테스트")
class PlayersTest {

    private Player makePlayer(String name) {
        return Player.of(name, 1000);
    }

    @DisplayName("플레이어가 1명 ~ 6명이고 이름이 중복되지 않으면 players를 생성한다")
    @Test
    void createPlayersTest() {
        Player test1 = makePlayer("pobi1");
        Player test2 = makePlayer("pobi2");
        Player test3 = makePlayer("pobi3");
        Player test4 = makePlayer("pobi4");
        Player test5 = makePlayer("pobi5");
        Player test6 = makePlayer("pobi6");

        assertThatCode(() -> new Players(List.of(test1, test2, test3, test4, test5, test6)))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어 이름이 중복되면 예외가 발생한다.")
    @Test
    void duplicateNameTest() {
        Player pobi = makePlayer("pobi");

        assertThatCode(() -> new Players(List.of(pobi, pobi)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복될 수 없습니다.");
    }


    @DisplayName("플레이어가 1명 미만이면 예외가 발생한다.")
    @Test
    void minimumPlayerTest() {
        assertThatCode(() -> new Players(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가능한 플레이어 슈는 1명 ~ 6명 입니다.");
    }

    @DisplayName("플레이어는 6명 초과이면 예외가 발생한다.")
    @Test
    void maxPlayerTest() {
        Player test1 = makePlayer("pobi1");
        Player test2 = makePlayer("pobi2");
        Player test3 = makePlayer("pobi3");
        Player test4 = makePlayer("pobi4");
        Player test5 = makePlayer("pobi5");
        Player test6 = makePlayer("pobi6");
        Player test7 = makePlayer("pobi7");

        assertThatCode(() -> new Players(List.of(test1, test2, test3, test4, test5, test6, test7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가능한 플레이어 슈는 1명 ~ 6명 입니다.");
    }
}