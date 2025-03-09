package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("플레이어와 딜러 모두 버스트라면 무승부다")
    @Test
    void judgeBustDraw() {
        //given
        Deck deck = new Deck();
        Card card1 = new Card(Symbol.COLVER, Rank.THREE);
        Card card2 = new Card(Symbol.HEART, Rank.SIX);
        Card card3 = new Card(Symbol.SPADE, Rank.JACK);
        Card card4 = new Card(Symbol.DIAMOND, Rank.JACK);
        Card card5 = new Card(Symbol.COLVER, Rank.SIX);
        Card card6 = new Card(Symbol.HEART, Rank.JACK);

        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);

        Dealer dealer = new Dealer();
        Player player = new Player("ad");

        dealer.prepareGame(deck);
        player.prepareGame(deck);

        dealer.hit(deck);
        player.hit(deck);

        //when
        Result actual = Result.judge(dealer.getDeck(), player.getDeck());

        //then
        assertThat(actual).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어와 딜러 모두 버스트가 아닐때 점수가 같다면 무승부다.")
    @Test
    void judgeNotBustDraw() {
        //given
        Deck deck = new Deck();
        Card card1 = new Card(Symbol.COLVER, Rank.TWO);
        Card card2 = new Card(Symbol.HEART, Rank.TWO);
        Card card3 = new Card(Symbol.SPADE, Rank.SIX);
        Card card4 = new Card(Symbol.DIAMOND, Rank.JACK);
        Card card5 = new Card(Symbol.COLVER, Rank.SIX);
        Card card6 = new Card(Symbol.HEART, Rank.JACK);

        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);

        Dealer dealer = new Dealer();
        Player player = new Player("ad");

        dealer.prepareGame(deck);
        player.prepareGame(deck);

        dealer.hit(deck);
        player.hit(deck);

        //when
        Result actual = Result.judge(dealer.getDeck(), player.getDeck());

        //then
        assertThat(actual).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어와 딜러 중 한쪽만 버스트라면 점수에 상관없이 버스트가 아닌 쪽이 승리한다.")
    @Test
    void judgeOnlyOneBustWin() {
        //given
        Deck deck = new Deck();
        Card card1 = new Card(Symbol.COLVER, Rank.TWO);
        Card card2 = new Card(Symbol.HEART, Rank.TWO);
        Card card3 = new Card(Symbol.SPADE, Rank.JACK);
        Card card4 = new Card(Symbol.DIAMOND, Rank.JACK);
        Card card5 = new Card(Symbol.COLVER, Rank.SIX);
        Card card6 = new Card(Symbol.HEART, Rank.JACK);

        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);

        Dealer dealer = new Dealer();
        Player player = new Player("ad");

        dealer.prepareGame(deck);
        player.prepareGame(deck);

        dealer.hit(deck);
        player.hit(deck);

        //when //then
        Result actual = Result.judge(dealer.getDeck(), player.getDeck());

        assertThat(actual).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어와 딜러 중 둘다 버스트가 아니고, 점수가 서로 다를경우 점수가 21에 가까운쪽이 승리한다.")
    @Test
    void judge() {
        //given
        Deck deck = new Deck();
        Card card1 = new Card(Symbol.COLVER, Rank.ACE);
        Card card2 = new Card(Symbol.HEART, Rank.TWO);
        Card card3 = new Card(Symbol.SPADE, Rank.JACK);
        Card card4 = new Card(Symbol.DIAMOND, Rank.JACK);
        Card card5 = new Card(Symbol.COLVER, Rank.SIX);
        Card card6 = new Card(Symbol.HEART, Rank.JACK);

        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);

        Dealer dealer = new Dealer();
        Player player = new Player("ad");

        dealer.prepareGame(deck);
        player.prepareGame(deck);

        dealer.hit(deck);
        player.hit(deck);

        //when
        Result actual = Result.judge(dealer.getDeck(), player.getDeck());

        //then
        assertThat(actual).isEqualTo(Result.LOSE);
    }

}
