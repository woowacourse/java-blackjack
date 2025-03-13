package domain.rule;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSymbol;
import domain.card.Hand;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackMatchResultTest {

    @DisplayName("플레이어와 딜러 모두 버스트라면 무승부다")
    @Test
    void judgeBurstDraw() {
        //given
        Card card1 = new Card(CardSymbol.COLVER, CardRank.KING);
        Card card2 = new Card(CardSymbol.COLVER, CardRank.THREE);
        Card card3 = new Card(CardSymbol.COLVER, CardRank.TWO);

        Hand dealerHand = HandFixture.createHand(card1, card1, card2);
        Hand playerHand = HandFixture.createHand(card1, card1, card3);

        //when
        BlackjackMatchResult actual = dealerHand.determineMatchResultAgainst(playerHand);

        //then
        assertThat(actual).isEqualTo(BlackjackMatchResult.DRAW);
    }

    @DisplayName("플레이어와 딜러 모두 버스트가 아닐때 점수가 같다면 무승부다.")
    @Test
    void judgeNotBurstDraw() {
        //given
        Card card1 = new Card(CardSymbol.COLVER, CardRank.KING);
        Card card2 = new Card(CardSymbol.COLVER, CardRank.THREE);

        Hand dealerHand = HandFixture.createHand(card1, card1);
        Hand playerHand = HandFixture.createHand(card1, card1);

        //when
        BlackjackMatchResult actual = dealerHand.determineMatchResultAgainst(playerHand);

        //then
        assertThat(actual).isEqualTo(BlackjackMatchResult.DRAW);
    }

    @DisplayName("플레이어와 딜러 중 한쪽만 버스트라면 점수에 상관없이 버스트가 아닌 쪽이 승리한다.")
    @Test
    void judgeOnlyOneBurstWin() {
        //given
        Card card1 = new Card(CardSymbol.COLVER, CardRank.KING);
        Card card2 = new Card(CardSymbol.COLVER, CardRank.THREE);
        Card card3 = new Card(CardSymbol.COLVER, CardRank.TWO);

        Hand dealerHand = HandFixture.createHand(card1, card2);
        Hand playerHand = HandFixture.createHand(card1, card1, card3);

        //when //then
        BlackjackMatchResult actual = dealerHand.determineMatchResultAgainst(playerHand);

        assertThat(actual).isEqualTo(BlackjackMatchResult.WIN);
    }

    @DisplayName("플레이어와 딜러 중 둘다 버스트가 아니고, 점수가 서로 다를경우 점수가 21에 가까운쪽이 승리한다.")
    @Test
    void judge() {
        //given
        Card card1 = new Card(CardSymbol.COLVER, CardRank.KING);
        Card card2 = new Card(CardSymbol.COLVER, CardRank.THREE);
        Card card3 = new Card(CardSymbol.COLVER, CardRank.TWO);

        Hand dealerHand = HandFixture.createHand(card1, card3);
        Hand playerHand = HandFixture.createHand(card1, card2);

        //when
        BlackjackMatchResult actual = dealerHand.determineMatchResultAgainst(playerHand);

        //then
        assertThat(actual).isEqualTo(BlackjackMatchResult.LOSE);
    }

}
