package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.exception.DuplicatedArgumentException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름이 중복되면 예외가 발생한다")
    void checking_player_name_duplicated() {
        assertThrows(DuplicatedArgumentException.class,
                () -> Players.from(List.of("pobi", "pobi")));
    }

    @Test
    @DisplayName("challenger만 반환하는지 테스트")
    void return_challengers() {
        Players players = Players.from(List.of("pobi", "oing"));
        List<Challenger> challengers = players.getChallengers();

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
