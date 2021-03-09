package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import blackjack.domain.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ResultStatisticsTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.NINE));
        dealer = new Dealer(new Cards(cardList));
    }

    @Test
    @DisplayName("챌린저가 1000원을 배팅하고, 블랙잭이며, 딜러는 블랙잭이 아니면 챌린저의 수익금은 1500원이다.")
    void challengerBlackJackProfit() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challenger.betting(new BetMoney(1000));

        challengers.add(challenger);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);
        assertThat(resultStatistics.getChallengerProfit(challenger)).isEqualTo(1500);
        assertThat(resultStatistics.getDealerProfit()).isEqualTo(-1500);
    }

    @Test
    @DisplayName("챌린저가 1000원을 배팅하고, 블랙잭이며, 딜러도 블랙잭이면 챌린저의 수익금은 0원이다.")
    void challengerBlackJackDouble() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challenger.betting(new BetMoney(1000));

        challengers.add(challenger);

        Dealer blackJackDealer = new Dealer(new Cards(cardList));

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), blackJackDealer);
        assertThat(resultStatistics.getChallengerProfit(challenger)).isEqualTo(0);
        assertThat(resultStatistics.getDealerProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("챌린저가 1000원을 배팅하고, 블랙잭이 아니며, 딜러보다 점수가 높으면(20점 vs 19점), 챌린저의 수익금은 1000원이다.")
    void challengerWinProfit() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challenger.betting(new BetMoney(1000));

        challengers.add(challenger);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);
        assertThat(resultStatistics.getChallengerProfit(challenger)).isEqualTo(1000);
        assertThat(resultStatistics.getDealerProfit()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("챌린저가 1000원을 배팅하고, 블랙잭이 아니며, 딜러보다 점수가 높으면(3장 21점 vs 19점), 챌린저의 수익금은 1000원이다.")
    void challengerWinProfit21() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challenger.betting(new BetMoney(1000));

        challengers.add(challenger);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);
        assertThat(resultStatistics.getChallengerProfit(challenger)).isEqualTo(1000);
        assertThat(resultStatistics.getDealerProfit()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("챌린저가 1000원을 배팅하고, 블랙잭이 아니며, 딜러보다 점수가 낮으면(18점 vs 19점), 챌린저의 수익금은 -1000원이다.")
    void challengerLoseProfit() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.EIGHT));    // 챌린저 점수는 18

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challenger.betting(new BetMoney(1000));

        challengers.add(challenger);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);
        assertThat(resultStatistics.getChallengerProfit(challenger)).isEqualTo(-1000);
        assertThat(resultStatistics.getDealerProfit()).isEqualTo(1000);
    }

    @Test
    @DisplayName("챌린저가 1000원을 배팅하고, 버스트(28)가 나며, 딜러는 버스트가 아니면, 챌린저의 수익금은 -1000원이다.")
    void challengerLoseProfitWithBust() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.EIGHT));
        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challenger.betting(new BetMoney(1000));

        challengers.add(challenger);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);
        assertThat(resultStatistics.getChallengerProfit(challenger)).isEqualTo(-1000);
        assertThat(resultStatistics.getDealerProfit()).isEqualTo(1000);
    }

    @Test
    @DisplayName("챌린저가 1000원을 배팅하고, 버스트(28)가 나며, 딜러도 버스트(28)이면 챌린저의 수익금은 -1000원이다.")
    void challengerLoseProfitWithDoubleBust() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.EIGHT));
        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challenger.betting(new BetMoney(1000));

        challengers.add(challenger);

        Dealer dealerBust = new Dealer(new Cards(cardList));

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealerBust);
        assertThat(resultStatistics.getChallengerProfit(challenger)).isEqualTo(-1000);
        assertThat(resultStatistics.getDealerProfit()).isEqualTo(1000);
    }


    @Test
    @DisplayName("챌린저가 1000원을 배팅하고, 챌린저 딜려 양측이 전부 버스트와 블랙잭이 아니며, 동점(19)이면 챌린저의 수익금은 0원이다.")
    void challengerDraw() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.NINE));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challenger.betting(new BetMoney(1000));

        challengers.add(challenger);
        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);
        assertThat(resultStatistics.getChallengerProfit(challenger)).isEqualTo(0);
        assertThat(resultStatistics.getDealerProfit()).isEqualTo(0);
    }


    @Test
    @DisplayName("3명의 챌린저가 1000원을 배팅하고 승,패,패 이면, 딜러의 수익금은 1000원이다.")
    void dealerMultiWin() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger1 = new Challenger(new Cards(cardList), new Name("pobi"));
        challenger1.betting(new BetMoney(1000));

        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        Challenger challenger2 = new Challenger(new Cards(cardList), new Name("json"));
        challenger2.betting(new BetMoney(1000));

        Challenger challenger3 = new Challenger(new Cards(cardList), new Name("brown"));
        challenger3.betting(new BetMoney(1000));

        challengers.add(challenger1);
        challengers.add(challenger2);
        challengers.add(challenger3);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);
        assertThat(resultStatistics.getDealerProfit()).isEqualTo(1000);
    }
}