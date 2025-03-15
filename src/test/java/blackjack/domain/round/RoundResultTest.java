package blackjack.domain.round;

import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.fixture.DeckFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoundResultTest {

    @Test
    @DisplayName("상대방만 버스트했다면 카드 숫자 합에 상관없이 승리한다")
    void judgeResultTest1() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.TWO);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.KING, CardNumber.QUEEN, CardNumber.NINE);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw(), dealerDeck.draw());

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
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

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
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw(), dealerDeck.draw());

        // when
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);

        // then
        assertThat(roundResult).isEqualTo(RoundResult.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("둘 다 블랙잭이면 무승부로 처리한다")
    void judgeResultTest4() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);

        // then
        assertThat(roundResult).isEqualTo(RoundResult.TIE);
    }

    @Test
    @DisplayName("블랙잭이 없다면 무승부로 처리한다")
    void judgeResultTest5() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.TWO);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.TWO);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);

        // then
        assertThat(roundResult).isEqualTo(RoundResult.TIE);
    }

    @Test
    @DisplayName("버스트될 경우 상대방의 결과와 상관없이 버스트로 패한다")
    void judgeResultTest6() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.SEVEN, CardNumber.EIGHT);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.KING, CardNumber.QUEEN);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);

        // then
        assertThat(roundResult).isEqualTo(RoundResult.BUST);
    }
}
