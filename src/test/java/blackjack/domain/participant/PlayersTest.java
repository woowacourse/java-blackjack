package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersTest {


    @Test
    @DisplayName("카드를 뽑을 수 있는 플레이어를 찾는다.")
    void findHitPlayer() {
        // give
        final Players players = new Players(List.of(
                new Player("rick", 1000),
                new Player("pobi", 1000)));

        // when
        final Player actual = players.findDrawablePlayer().get();

        // then
        assertThat(actual.getName()).isEqualTo("rick");
    }

    @ParameterizedTest
    @ValueSource(strings = {"rick", "pobi", "jason"})
    @DisplayName("이름으로 플레이어를 찾는다.")
    void findByName(String target) {
        // give
        final Players players = new Players(List.of(
                new Player("rick", 1000),
                new Player("pobi", 1000),
                new Player("jason", 1000)));

        // when
        final Player actual = players.findByName(target);

        // then
        assertThat(actual.getName()).isEqualTo(target);
    }

    @Test
    @DisplayName("모든 플레이어들의 이름을 반환한다.")
    void getNames() {
        // give
        final Players players = new Players(List.of(
                new Player("rick", 1000),
                new Player("pobi", 1000),
                new Player("jason", 1000)));

        // when
        final List<String> actual = players.getNames();

        // then
        assertThat(actual).isEqualTo(List.of("rick", "pobi", "jason"));
    }
}