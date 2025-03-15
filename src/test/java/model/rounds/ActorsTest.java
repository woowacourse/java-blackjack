package model.rounds;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ActorsTest {
    @DisplayName("딜러 객체를 생성할 수 있다")
    @Test
    void inviteDealer() {
        Actors actors = new Actors();

        actors.inviteDealer();

        assertThat(actors.getDealer()).isNotNull();
    }

    @DisplayName("플레이어들 객체를 생성할 수 있다")
    @Test
    void invitePlayer() {
        Actors actors = new Actors();
        actors.inviteDealer();

        actors.invitePlayers(actors.getDealer(), List.of("a", "b"), List.of(1000, 2000));

        assertThat(actors.getPlayers()).isNotNull();
    }
}
