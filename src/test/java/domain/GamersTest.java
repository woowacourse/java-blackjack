package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamersTest {
    @DisplayName("게이머들의 이름 목록을 반환온다.")
    @Test
    void getGamerNames() {
        // given
        List<Gamer> gamerList = List.of(
                new Gamer("pobi"),
                new Gamer("honux")
        );
        Gamers gamers = new Gamers(gamerList);

        // when
        List<String> gamerNames = gamers.getNames();

        // then
        assertThat(gamerNames).containsExactly("pobi", "honux");
    }
}
