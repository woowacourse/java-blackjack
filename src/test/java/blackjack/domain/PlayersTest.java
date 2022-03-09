package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.Denomination.*;
import static blackjack.domain.Symbol.*;
import static blackjack.domain.Symbol.HEART;
import static org.assertj.core.api.Assertions.*;

class PlayersTest {

    Player first;
    Player second;
    Players players;

    @BeforeEach
    void setUp() {

        first = new Player("pobi", List.of(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT)));
        second = new Player("jason", List.of(new Card(SPADE, JACK), new Card(HEART, ACE)));
        players = new Players(List.of(first, second));
    }

    @Test
    @DisplayName("현재 순서의 player를 반환한다")
    void testGetCurrentPlayer() {
        assertThat(players.getCurrentTurn()).isEqualTo(first);
    }

    @Test
    @DisplayName("순서를 다음 player로 넘긴다")
    void testPassTurnToNext() {
        players.passTurnToNext();
        assertThat(players.getCurrentTurn()).isEqualTo(second);
    }
}
