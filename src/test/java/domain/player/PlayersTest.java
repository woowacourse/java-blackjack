package domain.player;

import domain.deck.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {
    Players players;

    @BeforeEach
    void setup() {
        players = new Players(List.of("crong", "pobi", "hardy"));
    }

    @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
    @Test
    void drawTwoCardsAtFirstTimeTest() {
        int expectedCardSize = 2;
        players.drawTwoCardsAtFirstTime(new Deck());
        for (Player player : players.getPlayersWithDealer()) {
            assertEquals(expectedCardSize, player.getCards().size());
        }
    }

    @DisplayName("존재하지 않는 이름의 플레이어를 찾으면 예외를 던진다.")
    @Test
    void findPlayerFailTest() {
        String expectedPlayerName = "crong";
        assertThrows(IllegalArgumentException.class, () -> players.findPlayer("croong"))
                .getMessage().equals("[ERROR]: 해당 이름을 가진 플레이어가 존재하지 않습니다.");
    }

    @DisplayName("존재하는 이름의 플레이어를 찾는다.")
    @Test
    void findPlayerSuccessTest() {
        String expectedPlayerName = "crong";
        assertEquals(expectedPlayerName, players.findPlayer("crong").getName());
    }

    @DisplayName("딜러의 이름은 딜러이다.")
    @Test
    void findDealerTest() {
        String expectedDealerName = "딜러";
        assertTrue(players.findDealer() instanceof Dealer);
        assertEquals(expectedDealerName, players.findDealer().getName());
    }

    @DisplayName("딜러를 포함한 플레이어들을 반환시 첫 번째 원소는 딜러이다.")
    @Test
    void getPlayersWithDealerTest() {
        int expectedPlayersWithDealerSize = 4;
        String expectedDealerName = "딜러";
        List<Player> playersWithDealer = players.getPlayersWithDealer();

        assertEquals(expectedPlayersWithDealerSize, playersWithDealer.size());
        assertEquals(expectedDealerName, playersWithDealer.get(0).getName());
    }

    @DisplayName("딜러를 포함하지 않은 플레이어들을 반환시 첫 번째 원소는 딜러가 아니다.")
    @Test
    void getPlayersWithOutDealerTest() {
        int expectedPlayersWithOutDealerSize = 3;
        String expectedDealerName = "딜러";
        List<Player> playersWithOutDealer = players.getPlayersWithOutDealer();

        assertEquals(expectedPlayersWithOutDealerSize, playersWithOutDealer.size());
        assertNotEquals(expectedDealerName, playersWithOutDealer.get(0).getName());
    }
}
