package blackjack.domain;

import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.fixture.DeckFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerBetsTest {

    private Player player = new Player("Pobi");
    private Dealer dealer = new Dealer();

    @Test
    @DisplayName("플레이어가 블랙잭 없이 승리할 경우 베팅 금액의 1배를 수익으로 계산한다")
    void playerWinTest() {
        // given
        PlayerBets playerBets = new PlayerBets();
        playerBets.add(player, 10000);
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.NINE);
        player.initialize(playerDeck);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE);
        dealer.initialize(dealerDeck);

        // when
        double actual = playerBets.getPlayerProfit(player, dealer);

        // then
        assertThat(actual).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 승리할 경우 베팅 금액의 1.5배를 수익으로 계산한다")
    void playerBlackjackWinTest() {
        // given
        PlayerBets playerBets = new PlayerBets();
        playerBets.add(player, 10000);
        Deck playerDeck = DeckFixture.deckOf(CardNumber.ACE, CardNumber.JACK);
        player.initialize(playerDeck);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE);
        dealer.initialize(dealerDeck);

        // when
        double actual = playerBets.getPlayerProfit(player, dealer);

        // then
        assertThat(actual).isEqualTo(15000);
    }

    @Test
    @DisplayName("플레이어가 패배할 경우 수익을 0에서 베팅 금액을 차감한 값으로 계산한다")
    void playerLoseTest() {
        // given
        PlayerBets playerBets = new PlayerBets();
        playerBets.add(player, 20000);
        Deck playerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE);
        player.initialize(playerDeck);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.EIGHT, CardNumber.NINE);
        dealer.initialize(dealerDeck);

        // when
        double actual = playerBets.getPlayerProfit(player, dealer);

        // then
        assertThat(actual).isEqualTo(-20000);
    }

    @Test
    @DisplayName("딜러가 승리할 경우 플레이어가 베팅한 금액을 수익으로 계산한다")
    void dealerWinTest() {
        // given
        PlayerBets playerBets = new PlayerBets();
        List<Player> players = List.of(new Player("Pobi"), new Player("Neo"));
        Deck playerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE, CardNumber.FOUR, CardNumber.FIVE);
        for (Player player : players) {
            player.initialize(playerDeck);
            playerBets.add(player, 15000);
        }
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.EIGHT, CardNumber.NINE);
        dealer.initialize(dealerDeck);

        // when
        double actual = playerBets.getDealerProfit(dealer, players);

        // then
        assertThat(actual).isEqualTo(30000);
    }

    @Test
    @DisplayName("딜러가 패배할 경우 수익을 0으로 계산한다")
    void dealerLoseTest() {
        // given
        PlayerBets playerBets = new PlayerBets();
        List<Player> players = List.of(new Player("Pobi"), new Player("Neo"));
        Deck playerDeck = DeckFixture.deckOf(CardNumber.FOUR, CardNumber.FIVE, CardNumber.SIX, CardNumber.EIGHT);
        for (Player player : players) {
            player.initialize(playerDeck);
            playerBets.add(player, 15000);
        }
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE);
        dealer.initialize(dealerDeck);

        // when
        double actual = playerBets.getDealerProfit(dealer, players);

        // then
        assertThat(actual).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({
            "TWO,EIGHT,THREE,SEVEN,0",
            "ACE,JACK,ACE,KING,0",
    })
    @DisplayName("무승부일 경우 플레이어의 수익을 0으로 계산한다")
    void playerTieTest(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3,
                       CardNumber cardNumber4, double expected) {
        // given
        PlayerBets playerBets = new PlayerBets();
        playerBets.add(player, 20000);
        Deck playerDeck = DeckFixture.deckOf(cardNumber1, cardNumber2);
        player.initialize(playerDeck);
        Deck dealerDeck = DeckFixture.deckOf(cardNumber3, cardNumber4);
        dealer.initialize(dealerDeck);

        // when
        double actual = playerBets.getPlayerProfit(player, dealer);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
