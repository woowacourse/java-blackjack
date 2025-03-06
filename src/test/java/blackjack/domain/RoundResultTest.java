package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

class RoundResultTest {

    @Test
    @DisplayName("상대방만 버스트했다면 카드 숫자 합에 상관없이 승리한다")
    void winnerTest1() {
        Player player1 = new Player("Pobi");
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.TWO);

        Card card3 = new Card(CardType.CLOVER, CardNumber.KING);
        Card card4 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Card card5 = new Card(CardType.CLOVER, CardNumber.NINE);
        player1.addCard(card1);
        player1.addCard(card2);
        dealer.addCard(card3);
        dealer.addCard(card4);
        dealer.addCard(card5);

        RoundResult roundResult = RoundResult.judgeResult(player1, dealer);

        assertThat(roundResult).isEqualTo(RoundResult.WIN);
    }

    @Test
    @DisplayName("양쪽 다 버스트하지 않았다면 카드 숫자 합을 비교하여 더 높은 쪽이 승리한다")
    void winnerTest2() {
        Player player1 = new Player("Pobi");
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.TWO);

        Card card3 = new Card(CardType.CLOVER, CardNumber.THREE);
        Card card4 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        player1.addCard(card1);
        player1.addCard(card2);
        dealer.addCard(card3);
        dealer.addCard(card4);

        RoundResult roundResult = RoundResult.judgeResult(player1, dealer);

        assertThat(roundResult).isEqualTo(RoundResult.LOSE);
    }

    @Test
    @DisplayName("동점일 경우 블랙잭(Ace + J or Q or K)은 21을 이긴다")
    void winnerTest3() {
        Player player1 = new Player("Pobi");
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.ACE);

        Card card3 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Card card4 = new Card(CardType.CLOVER, CardNumber.NINE);
        Card card5 = new Card(CardType.CLOVER, CardNumber.TWO);
        player1.addCard(card1);
        player1.addCard(card2);
        dealer.addCard(card3);
        dealer.addCard(card4);
        dealer.addCard(card5);

        RoundResult roundResult = RoundResult.judgeResult(player1, dealer);

        assertThat(roundResult).isEqualTo(RoundResult.WIN);
    }

    @Test
    @DisplayName("둘 다 블랙잭이면 무승부로 처리한다")
    void winnerTest4() {
        Player player1 = new Player("Pobi");
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.ACE);

        Card card3 = new Card(CardType.HEART, CardNumber.JACK);
        Card card4 = new Card(CardType.HEART, CardNumber.ACE);
        player1.addCard(card1);
        player1.addCard(card2);
        dealer.addCard(card3);
        dealer.addCard(card4);

        RoundResult roundResult = RoundResult.judgeResult(player1, dealer);

        assertThat(roundResult).isEqualTo(RoundResult.TIE);
    }

    @Test
    @DisplayName("블랙잭이 없다면 무승부로 처리한다.")
    void winnerTest5() {
        Player player1 = new Player("Pobi");
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.TWO);

        Card card3 = new Card(CardType.HEART, CardNumber.JACK);
        Card card4 = new Card(CardType.HEART, CardNumber.TWO);
        player1.addCard(card1);
        player1.addCard(card2);
        dealer.addCard(card3);
        dealer.addCard(card4);

        RoundResult roundResult = RoundResult.judgeResult(player1, dealer);

        assertThat(roundResult).isEqualTo(RoundResult.TIE);
    }

    @Test
    void judgeResult() {
    }
}
