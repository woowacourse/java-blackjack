package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePlayerTest {

    private List<Player> players;

    @BeforeEach
    void init() {
        players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(new Name("newName")));
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
        GamePlayer gamePlayer = new GamePlayer(new Players(players), new Dealer());
        assertDoesNotThrow(() -> gamePlayer.giveCardToDealer(new Card(CardNumber.ACE, CardSymbol.HEARTS)));
    }

    @DisplayName("플레이어에게 카드를 나눠주는 메소드 테스트")
    @Test
    void Should_Success_When_GiveCardToPlayer() {
        GamePlayer gamePlayer = new GamePlayer(new Players(players), new Dealer());
        assertDoesNotThrow(() -> gamePlayer.giveCardToPlayerByIndex(1, new Card(CardNumber.ACE, CardSymbol.HEARTS)));
    }

    @DisplayName("게임 시작 시 딜러와 플레이어에게 카드 두 장을 나눠주는 메소드 테스트")
    @Test
    void Should_Success_When_init() {
        GamePlayer gamePlayer = new GamePlayer(new Players(players), new Dealer());
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card(CardNumber.ACE, CardSymbol.CLUBS);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.SPADES);
        Card card4 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        Card card5 = new Card(CardNumber.TEN, CardSymbol.DIAMONDS);
        Card card6 = new Card(CardNumber.TEN, CardSymbol.SPADES);
        Card card7 = new Card(CardNumber.TEN, CardSymbol.CLUBS);
        Card card8 = new Card(CardNumber.TEN, CardSymbol.HEARTS);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);

        assertDoesNotThrow(() -> gamePlayer.initializeGamePlayer(cards));
    }

}
