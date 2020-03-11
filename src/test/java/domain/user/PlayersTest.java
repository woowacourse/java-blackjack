package domain.user;

import factory.PlayerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @Test
    @DisplayName("Players 생성")
    void create() {
        assertThat(new Players(PlayerFactory.create("playerA,playerB"))).isInstanceOf(Players.class);
    }
}