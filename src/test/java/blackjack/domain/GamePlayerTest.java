package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePlayerTest {
    private GamePlayer gamePlayer;

    @BeforeEach
    void init() {
        gamePlayer = new GamePlayer(new Dealer(), Players.from(List.of("name1", "name2", "name3")));
    }

    @DisplayName("GamePlayer의 수는 players의 수보다 하나 많다.")
    @Test
    void Should_Create_When_NewGamePlayer() {
        assertThat(gamePlayer.getCount()).isEqualTo(4);
    }

    @DisplayName("딜러에게 카드를 나눠줄 수 있다.")
    @Test
    void Should_Success_When_GiveCardToDealer() {
        gamePlayer.dealerHit(new Card(CardNumber.ACE, CardSymbol.HEARTS));

        assertThat(gamePlayer.getDealer().getAllCards().get(0).getCardNumberToString()).isEqualTo("A");
    }

    @DisplayName("플레이어에게 카드를 나눠줄 수 있다.")
    @Test
    void Should_Success_When_GiveCardToPlayer() {
        int secondPlayerIndex = 1;
        gamePlayer.playerHit(secondPlayerIndex, new Card(CardNumber.ACE, CardSymbol.HEARTS));

        assertThat(gamePlayer.getPlayers().getPlayer(secondPlayerIndex).getAllCards().get(0)
                .getCardNumberToString()).isEqualTo("A");
    }

    @DisplayName("게임 시작 시 딜러와 플레이어에게 카드 두 장씩 나눠준다.")
    @Test
    void Should_Success_When_init() {
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
        gamePlayer.initializeGamePlayer(cards);

        assertThat(gamePlayer.getDealer().getAllCards().size()).isEqualTo(2);
    }
}
