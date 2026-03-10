package blackjack.domain;

import blackjack.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    @DisplayName("플레이어는 2명 이상 5명이어야 한다")
    void createPlayersTest() {
        // given
        Player playerOne = new Player("luke");
        Player playerTwo = new Player("usher");

        // when
        Players players = new Players(List.of(playerOne, playerTwo));
        List<Player> players1 = players.getPlayers();

        // then
        assertThat(players1.get(0).getName()).isEqualTo("luke");
        assertThat(players1.get(1).getName()).isEqualTo("usher");
    }

    @Test
    @DisplayName("플레이어는 2명 이상 5명 이하가 아닌 경우 예외를 발생한다")
    void createPlayersErrorTest() {
        // given
        Player playerOne = new Player("luke");

        // when & then
        assertThatThrownBy(() -> new Players(List.of(playerOne)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_PLAYERS.getMessage());
    }

}
