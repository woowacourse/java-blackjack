package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackMatchResultTest {

    @DisplayName("플레이어와 딜러 모두 버스트라면 무승부다")
    @Test
    void judgeBurstDraw() {
        //given
        Hand dealerHand = new Hand();
        Hand playerHand = new Hand();

        dealerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        dealerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        dealerHand.add(new Card(CardSymbol.COLVER, CardRank.TWO));

        playerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        playerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        playerHand.add(new Card(CardSymbol.COLVER, CardRank.THREE));
        //when

        BlackjackMatchResult actual = dealerHand.compareWith(playerHand);
        //then
        assertThat(actual).isEqualTo(BlackjackMatchResult.DRAW);
    }

    @DisplayName("플레이어와 딜러 모두 버스트가 아닐때 점수가 같다면 무승부다.")
    @Test
    void judgeNotBurstDraw() {
        //given
        Hand dealerHand = new Hand();
        Hand playerHand = new Hand();

        dealerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        dealerHand.add(new Card(CardSymbol.COLVER, CardRank.TWO));

        playerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        playerHand.add(new Card(CardSymbol.COLVER, CardRank.TWO));

        //when
        BlackjackMatchResult actual = dealerHand.compareWith(playerHand);

        //then
        assertThat(actual).isEqualTo(BlackjackMatchResult.DRAW);
    }

    @DisplayName("플레이어와 딜러 중 한쪽만 버스트라면 점수에 상관없이 버스트가 아닌 쪽이 승리한다.")
    @Test
    void judgeOnlyOneBurstWin() {
        //given
        Hand dealerHand = new Hand();
        Hand playerHand = new Hand();

        dealerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        dealerHand.add(new Card(CardSymbol.COLVER, CardRank.TWO));

        playerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        playerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        playerHand.add(new Card(CardSymbol.COLVER, CardRank.THREE));

        //when //then
        BlackjackMatchResult actual = dealerHand.compareWith(playerHand);

        assertThat(actual).isEqualTo(BlackjackMatchResult.WIN);
    }

    @DisplayName("플레이어와 딜러 중 둘다 버스트가 아니고, 점수가 서로 다를경우 점수가 21에 가까운쪽이 승리한다.")
    @Test
    void judge() {
        //given
        Hand dealerHand = new Hand();
        Hand playerHand = new Hand();

        dealerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        dealerHand.add(new Card(CardSymbol.COLVER, CardRank.TWO));

        playerHand.add(new Card(CardSymbol.COLVER, CardRank.KING));
        playerHand.add(new Card(CardSymbol.COLVER, CardRank.THREE));

        //when
        BlackjackMatchResult actual = dealerHand.compareWith(playerHand);

        //then
        assertThat(actual).isEqualTo(BlackjackMatchResult.LOSE);
    }

}
