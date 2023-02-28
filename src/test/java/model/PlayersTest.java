package model;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("두 명의 이름이 주어지면 딜러를 포함해서 3명의 Players 반환된다.")
    void createDealer() {
        // given, when
        final Players players = new Players(List.of("bebe", "ethan"));

        // then
        assertThat(players)
                .extracting("players", InstanceOfAssertFactories.list(Player.class))
                .containsExactly(new Player("딜러"), new Player("bebe"), new Player("ethan"));
    }
}
