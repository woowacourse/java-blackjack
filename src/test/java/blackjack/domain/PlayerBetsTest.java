package blackjack.domain;

import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.fixture.DeckFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
