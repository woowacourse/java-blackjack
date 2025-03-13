package domain;

import domain.bet.BlackJackBetCalculator;
import domain.card.Card;
import domain.card.CardGroup;
import domain.bet.Bet;
import domain.card.CardScore;
import domain.card.CardType;
import domain.gamer.Dealer;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackBetSettlementTest {
    private BlackJackBetCalculator calculator;
    Map<String, Integer> betMap;

    @BeforeEach
    void init(){
        betMap = new HashMap<>();
        betMap.put("가이온",1000);
        calculator = new BlackJackBetCalculator(betMap);
    }

    @Nested
    class playerWinningTest{
        @DisplayName("플레이어가 딜러를 상대로 승리한 경우 배팅한 금액만큼 얻는다")
        @Test
        void winningTest(){
            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온",winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup losingCardGroup = new CardGroup();
            Dealer dealer = new Dealer(losingCardGroup);

            int winningBet = calculator.determineBettingAmount(dealer, winningPlayer);

            assertThat(winningBet).isEqualTo(1000);
        }

        @DisplayName("플레이어가 딜러를 상대로 패배한 경우 배팅한 금액만큼 잃는다")
        @Test
        void losingTest(){
            CardGroup losingCardGroup = new CardGroup();
            Player losingPlayer = new Player("가이온",losingCardGroup);

            CardGroup winningCardGroup = new CardGroup();
            Dealer dealer = new Dealer(winningCardGroup);
            dealer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            int winningBet = calculator.determineBettingAmount(dealer, losingPlayer);

            assertThat(winningBet).isEqualTo(-1000);
        }

        @DisplayName("플레이어가 딜러를 상대로 무승부한 경우 배팅한 금액을 그래돌 돌려받는다")
        @Test
        void drawingTest(){
            CardGroup drawingPlayerCardGroup = new CardGroup();
            Player drawingPlayer = new Player("가이온",drawingPlayerCardGroup);
            drawingPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup drawingDealerCardGroup = new CardGroup();
            Dealer drawingDealer = new Dealer(drawingDealerCardGroup);
            drawingDealer.receiveCard(new Card(CardType.DIAMOND, CardScore.TEN));

            int drawingBet = calculator.determineBettingAmount(drawingDealer, drawingPlayer);

            assertThat(drawingBet).isEqualTo(0);
        }

        @DisplayName("플레이어가 블랙잭으로 승리한 경우 1.5배를 받는다")
        @Test
        void blackJackWinningTest(){
            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온",winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER,CardScore.TEN));
            winningPlayer.receiveCard(new Card(CardType.CLOVER,CardScore.ACE));


            CardGroup losingCardGroup = new CardGroup();
            Dealer dealer = new Dealer(losingCardGroup);

            int winningBet = calculator.determineBettingAmount(dealer, winningPlayer);

            assertThat(winningBet).isEqualTo(1500);
        }
    }
}
