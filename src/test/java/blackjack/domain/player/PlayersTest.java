package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.domain.player.exception.DuplicatedPlayerNameException;

import java.util.Collections;
import java.util.List;

import blackjack.domain.player.exception.InvalidChallengerNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("challenger만 반환하는지 테스트")
    void return_challengers() {
        Players players = Players.from(List.of("pobi", "oing"));
        List<Player> challengers = players.getChallengers();

        challengers.forEach(challenger ->
                assertThat(challenger).isInstanceOf(Challenger.class));
    }

    @Test
    @DisplayName("dealer만 반환하는지 테스트")
    void return_dealer() {
        Players players = Players.from(List.of("pobi", "oing"));
        Player dealer = players.getDealer();

        assertThat(dealer).isInstanceOf(Dealer.class);
    }
}
