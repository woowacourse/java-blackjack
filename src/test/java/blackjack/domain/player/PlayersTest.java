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
    @DisplayName("플레이어의 이름이 중복되면 예외가 발생한다")
    void checking_player_name_duplicated() {
        assertThrows(DuplicatedPlayerNameException.class,
                () -> Players.from(List.of("pobi", "pobi")));
    }

    @Test
    @DisplayName("도전자의 숫자가 10명이 초과되면 예외가 발생한다.")
    void over_challenger_number() {
        assertThrows(InvalidChallengerNumberException.class,
                () -> Players.from(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")));
    }

    @Test
    @DisplayName("도전자의 숫자가 1명 미만이면 예외가 발생한다.")
    void under_challenger_number() {
        assertThrows(InvalidChallengerNumberException.class,
                () -> Players.from(Collections.emptyList()));
    }

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
