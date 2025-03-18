package model.betting;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import model.card.Card;
import model.card.CardRank;
import model.card.CardSuit;
import model.participant.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {
    @Test
    @DisplayName("보험을 걸고 딜러가 블랙잭일 경우 테스트")
    void 보험을_걸고_딜러가_블랙잭일_경우_테스트(){
        Betting betting = new Betting(10000);
        betting.updateInsuranceBet(4000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        int expect = 8000;
        int insuranceResult = betting.calculateInsurance(dealer);
        assertThat(expect).isEqualTo(insuranceResult);
    }

    @Test
    @DisplayName("보험을 걸고 딜러가 블랙잭이 아닌 경우 테스트")
    void 보험을_걸고_딜러가_블랙잭이_아닐_경우_테스트(){
        Betting betting = new Betting(10000);
        betting.updateInsuranceBet(5000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.NINE, CardSuit.CLOVER));
        int expect = 0;
        int insuranceResult = betting.calculateInsurance(dealer);
        assertThat(expect).isEqualTo(insuranceResult);
    }

    @Test
    @DisplayName("플레이어가 서렌을 했을 경우 손익결과 확인 테스트")
    void 플레이어가_서렌을_했을_경우_블랙잭이_아닐_경우_손익결과_확인테스트(){
        Betting betting = new Betting(10000);
        int expect = -5000;
        int insuranceResult = betting.calculateSurrender();
        assertThat(expect).isEqualTo(insuranceResult);
    }

}