package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.RoundResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

// REAL LAST TODO 테스트 리팩토링(클래스 분리, 단위테스트, ParameterizedTest, 픽스쳐)
class BlackjackTest {

    @Test
    @DisplayName("카드는 4가지 문양을 가질 수 있다")
    void cardTypeTest() {
        assertThat(CardType.values()).hasSize(4);
    }

    @Test
    @DisplayName("J, Q, K는 각각 10으로 계산한다")
    void cardNumberTest() {
        assertThat(CardNumber.JACK.getNumber()).isEqualTo(10);
        assertThat(CardNumber.QUEEN.getNumber()).isEqualTo(10);
        assertThat(CardNumber.KING.getNumber()).isEqualTo(10);
    }

    @Test
    @DisplayName("플레이어는 카드 두 장을 지급 받는다")
    void playerGetCardsTest() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.THREE);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("21이하인 경우 버스트되지 않는다")
    void additionalCardBustTest1() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("21초과인 경우 버스트된다")
    void additionalCardBustTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Card card3 = new Card(CardType.CLOVER, CardNumber.EIGHT);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("버스트되었을 경우 카드를 추가로 지급받을 수 없다")
    void additionalCardFalseTest() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Card card3 = new Card(CardType.CLOVER, CardNumber.EIGHT);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.canReceiveAdditionalCards()).isFalse();
    }

    @Test
    @DisplayName("버스트되지 않았을 경우 카드를 추가로 지급받을 수 있다")
    void additionalCardFalseTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.canReceiveAdditionalCards()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드 숫자 합이 16 이하이면 카드를 추가로 받을 수 있다")
    void dealerAdditionalCardTest() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.TWO);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.canReceiveAdditionalCards()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드 숫자 합이 16 초과면 카드를 추가로 받을 수 없다")
    void dealerAdditionalCardTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.canReceiveAdditionalCards()).isFalse();
    }

    @Test
    @DisplayName("버스트되지 않으면 Ace는 11로 계산한다")
    void aceNotBustTest1() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.ACE);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.getSumOfCards()).isEqualTo(21);
    }

    @Test
    @DisplayName("버스트되면 Ace는 1로 계산한다")
    void aceNotBustTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.TWO);
        Card card3 = new Card(CardType.CLOVER, CardNumber.ACE);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);
        dealer.addCard(card3);

        assertThat(dealer.getSumOfCards()).isEqualTo(13);
    }

    @Test
    @DisplayName("뽑히는 카드는 중복되지 않는다")
    void randomCardsNotDuplicatedTest() {
        Deck deck = new Deck(new RandomCardStrategy());
        Stack<Card> cards = deck.getAll();
        assertThat(cards.stream().distinct().count()).isEqualTo(cards.size());
    }

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
}
