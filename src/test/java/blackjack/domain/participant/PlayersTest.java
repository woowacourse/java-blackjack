package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.deck.Card;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("게임의 플레이어는 최소 1명이상이 참여해야 합니다")
    @Test
    void should_ThrowIllegalArgumentException_When_Lower_Than_Minimum_PlayerNumber() {
        List<Player> playerNames = List.of();
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참여자는 최소 1명에서 최대 8명까지 가능합니다");
    }

    @DisplayName("게임의 플레이어는 최대 8명까지 참여할 수 있습니다")
    @Test
    void should_ThrowIllegalArgumentException_When_More_Than_Maximum_PlayerNumber() {

        List<Player> players = Stream.iterate(1, (i) -> i + 1).
                limit(9)
                .map((input) -> Player.createPlayer(new Name(input.toString()),
                        List.of(Card.valueOf(0),
                                Card.valueOf(9)),
                        new BetMoney(input))).toList();
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참여자는 최소 1명에서 최대 8명까지 가능합니다");
    }
}
