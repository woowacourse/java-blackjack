package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.fixture.BlackjackControllerFixture;
import blackjack.fixture.DeckFixture;

class RoundResultTest {

    private Player player= new Player("Pobi");;
    private Dealer dealer = new Dealer();

    @Test
    @DisplayName("상대방만 버스트했다면 카드 숫자 합에 상관없이 승리한다")
    void judgeResultTest1() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.TWO);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.KING, CardNumber.QUEEN, CardNumber.NINE);
        BlackjackControllerFixture.BlackjackControllerWith(playerDeck).drawStartingCards(player);
        BlackjackControllerFixture.BlackjackControllerWith(dealerDeck).drawStartingCards(dealer);
        dealer.drawCard(dealerDeck);

        // when
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);

        // then
        assertThat(roundResult).isEqualTo(RoundResult.WIN);
    }

    @Test
    @DisplayName("양쪽 다 버스트하지 않았다면 카드 숫자 합을 비교하여 더 높은 쪽이 승리한다")
    void judgeResultTest2() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.TWO);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.THREE, CardNumber.QUEEN);
        BlackjackControllerFixture.BlackjackControllerWith(playerDeck).drawStartingCards(player);
        BlackjackControllerFixture.BlackjackControllerWith(dealerDeck).drawStartingCards(dealer);

        // when
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);

        // then
        assertThat(roundResult).isEqualTo(RoundResult.LOSE);
    }

    @Test
    @DisplayName("동점일 경우 블랙잭(Ace + J or Q or K)은 21을 이긴다")
    void judgeResultTest3() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.QUEEN, CardNumber.NINE, CardNumber.TWO);
        BlackjackControllerFixture.BlackjackControllerWith(playerDeck).drawStartingCards(player);
        BlackjackControllerFixture.BlackjackControllerWith(dealerDeck).drawStartingCards(dealer);
        dealer.drawCard(dealerDeck);

        // when
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);

        // then
        assertThat(roundResult).isEqualTo(RoundResult.WIN);
    }

    @Test
    @DisplayName("둘 다 블랙잭이면 무승부로 처리한다")
    void judgeResultTest4() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE);
        BlackjackControllerFixture.BlackjackControllerWith(playerDeck).drawStartingCards(player);
        BlackjackControllerFixture.BlackjackControllerWith(dealerDeck).drawStartingCards(dealer);

        // when
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);

        // then
        assertThat(roundResult).isEqualTo(RoundResult.TIE);
    }

    @Test
    @DisplayName("블랙잭이 없다면 무승부로 처리한다.")
    void judgeResultTest5() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.TWO);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.TWO);
        BlackjackControllerFixture.BlackjackControllerWith(playerDeck).drawStartingCards(player);
        BlackjackControllerFixture.BlackjackControllerWith(dealerDeck).drawStartingCards(dealer);

        // when
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);

        // then
        assertThat(roundResult).isEqualTo(RoundResult.TIE);
    }
}
