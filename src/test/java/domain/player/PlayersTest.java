package domain.player;

import domain.deck.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayersTest {

    Players players;

    @BeforeEach
    void setup() {
        players = new Players(List.of("crong", "pobi", "hardy"));
    }

    @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
    @Test
    void hitTwiceTest() {
        int expectedCardSize = 2;
        players.hitTwice(new Deck());
        for (Player player : players.getPlayers()) {
            assertEquals(expectedCardSize, player.getCards().size());
        }
    }

    @DisplayName("존재하지 않는 이름의 플레이어를 찾으면 예외를 던진다.")
    @Test
    void findPlayerFailTest() {
        assertThatThrownBy(() -> players.findPlayer("croong"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 해당 이름을 가진 플레이어가 존재하지 않습니다.");
    }

    @DisplayName("존재하는 이름의 플레이어를 찾는다.")
    @Test
    void findPlayerSuccessTest() {
        String expectedPlayerName = "crong";
        assertEquals(expectedPlayerName, players.findPlayer("crong").getName());
    }
}
