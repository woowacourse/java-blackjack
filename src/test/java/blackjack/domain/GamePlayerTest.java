package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePlayerTest {
    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewGamePlayer() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(new Name("newName")));
        }
        assertDoesNotThrow(() -> new GamePlayer(new Players(players), new Dealer()));
    }

    @DisplayName("딜러에게 카드를 나눠주는 메소드 테스트")
    @Test
    void Should_Success_When_GiveCardToDealer() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(new Name("newName")));
        }
        GamePlayer gamePlayer = new GamePlayer(new Players(players), new Dealer());
        assertDoesNotThrow(() -> gamePlayer.giveCardToDealer(new Card(CardNumber.ACE, CardSymbol.HEARTS)));
    }

    @DisplayName("플레이어에게 카드를 나눠주는 메소드 테스트")
    @Test
    void Should_Success_When_GiveCardToPlayer() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(new Name("newName")));
        }
        GamePlayer gamePlayer = new GamePlayer(new Players(players), new Dealer());
        assertDoesNotThrow(() -> gamePlayer.giveCardToPlayerByIndex(1, new Card(CardNumber.ACE, CardSymbol.HEARTS)));
    }

}
