package blackjack.domain.gameplayer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GamePlayerTest {

    private List<Player> players;

    @BeforeEach
    void init() {
        players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(new Name("newName" + i)));
        }
    }

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewGamePlayer() {
        assertDoesNotThrow(() -> new GamePlayer(new Players(players), new Dealer()));
    }

    @DisplayName("딜러에게 카드를 나눠주는 메소드 테스트")
    @Test
    void Should_Success_When_GiveCardToDealer() {
        // given
        GamePlayer gamePlayer = new GamePlayer(new Players(players), new Dealer());

        // when, then
        assertDoesNotThrow(() -> gamePlayer.giveCardToDealer(new Card(CardNumber.ACE, CardSymbol.HEARTS)));
    }

    @DisplayName("플레이어에게 카드를 나눠주는 메소드 테스트")
    @Test
    void Should_Success_When_GiveCardToPlayer() {
        // given
        GamePlayer gamePlayer = new GamePlayer(new Players(players), new Dealer());

        // when, then
        assertDoesNotThrow(() -> gamePlayer.giveCardTo(players.get(0), new Card(CardNumber.ACE, CardSymbol.HEARTS)));
    }
}
