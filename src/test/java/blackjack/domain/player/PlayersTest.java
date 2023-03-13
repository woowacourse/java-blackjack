package blackjack.domain.player;

import blackjack.dto.ChallengerNameAndMoneyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    private Players players;

    @BeforeEach
    void setup() {
        ChallengerNameAndMoneyDto ditoo = new ChallengerNameAndMoneyDto(new ChallengerName("ditoo"), Money.from(1000));
        ChallengerNameAndMoneyDto bada = new ChallengerNameAndMoneyDto(new ChallengerName("bada"), Money.from(1000));
        players = Players.from(List.of(ditoo, bada));
    }

    @Test
    @DisplayName("challenger만 반환하는지 테스트")
    void return_challengers() {
        List<Challenger> challengers = players.getChallengers();

        challengers.forEach(challenger ->
                assertThat(challenger).isInstanceOf(Challenger.class));
    }

    @Test
    @DisplayName("dealer만 반환하는지 테스트")
    void return_dealer() {
        Player dealer = players.getDealer();

        assertThat(dealer).isInstanceOf(Dealer.class);
    }
}
