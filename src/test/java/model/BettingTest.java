package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import model.betting.Betting;
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
        betting.takeInsurance(5000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        int expect = 10000;
        int insuranceResult = betting.calculateInsurance(dealer);
        assertThat(expect).isEqualTo(insuranceResult);
    }
}