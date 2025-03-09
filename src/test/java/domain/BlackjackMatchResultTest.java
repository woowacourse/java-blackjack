package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackMatchResultTest {

    @DisplayName("플레이어와 딜러 모두 버스트라면 무승부다")
    @Test
    void judgeBurstDraw() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, CardRank.THREE);
        Card card2 = new Card(Symbol.HEART, CardRank.SIX);
        Card card3 = new Card(Symbol.SPADE, CardRank.JACK);
        Card card4 = new Card(Symbol.DIAMOND, CardRank.JACK);
        Card card5 = new Card(Symbol.COLVER, CardRank.SIX);
        Card card6 = new Card(Symbol.HEART, CardRank.JACK);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        Dealer dealer = new Dealer();
        Player player = new Player("ad");

        dealer.prepareGame(cards);
        player.prepareGame(cards);

        dealer.hit(cards);
        player.hit(cards);

        //when
        BlackjackMatchResult actual = BlackjackMatchResult.judge(dealer.getCards(), player.getCards());

        //then
        assertThat(actual).isEqualTo(BlackjackMatchResult.DRAW);
    }

    @DisplayName("플레이어와 딜러 모두 버스트가 아닐때 점수가 같다면 무승부다.")
    @Test
    void judgeNotBurstDraw() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, CardRank.TWO);
        Card card2 = new Card(Symbol.HEART, CardRank.TWO);
        Card card3 = new Card(Symbol.SPADE, CardRank.SIX);
        Card card4 = new Card(Symbol.DIAMOND, CardRank.JACK);
        Card card5 = new Card(Symbol.COLVER, CardRank.SIX);
        Card card6 = new Card(Symbol.HEART, CardRank.JACK);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        Dealer dealer = new Dealer();
        Player player = new Player("ad");

        dealer.prepareGame(cards);
        player.prepareGame(cards);

        dealer.hit(cards);
        player.hit(cards);

        //when
        BlackjackMatchResult actual = BlackjackMatchResult.judge(dealer.getCards(), player.getCards());

        //then
        assertThat(actual).isEqualTo(BlackjackMatchResult.DRAW);
    }

    @DisplayName("플레이어와 딜러 중 한쪽만 버스트라면 점수에 상관없이 버스트가 아닌 쪽이 승리한다.")
    @Test
    void judgeOnlyOneBurstWin() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, CardRank.TWO);
        Card card2 = new Card(Symbol.HEART, CardRank.TWO);
        Card card3 = new Card(Symbol.SPADE, CardRank.JACK);
        Card card4 = new Card(Symbol.DIAMOND, CardRank.JACK);
        Card card5 = new Card(Symbol.COLVER, CardRank.SIX);
        Card card6 = new Card(Symbol.HEART, CardRank.JACK);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        Dealer dealer = new Dealer();
        Player player = new Player("ad");

        dealer.prepareGame(cards);
        player.prepareGame(cards);

        dealer.hit(cards);
        player.hit(cards);

        //when //then
        BlackjackMatchResult actual = BlackjackMatchResult.judge(dealer.getCards(), player.getCards());

        assertThat(actual).isEqualTo(BlackjackMatchResult.WIN);
    }

    @DisplayName("플레이어와 딜러 중 둘다 버스트가 아니고, 점수가 서로 다를경우 점수가 21에 가까운쪽이 승리한다.")
    @Test
    void judge() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, CardRank.ACE);
        Card card2 = new Card(Symbol.HEART, CardRank.TWO);
        Card card3 = new Card(Symbol.SPADE, CardRank.JACK);
        Card card4 = new Card(Symbol.DIAMOND, CardRank.JACK);
        Card card5 = new Card(Symbol.COLVER, CardRank.SIX);
        Card card6 = new Card(Symbol.HEART, CardRank.JACK);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        Dealer dealer = new Dealer();
        Player player = new Player("ad");

        dealer.prepareGame(cards);
        player.prepareGame(cards);

        dealer.hit(cards);
        player.hit(cards);

        //when
        BlackjackMatchResult actual = BlackjackMatchResult.judge(dealer.getCards(), player.getCards());

        //then
        assertThat(actual).isEqualTo(BlackjackMatchResult.LOSE);
    }

}
