package domain;

import domain.bet.BlackJackBetCalculator;
import domain.card.Card;
import domain.card.CardGroup;
import domain.card.CardScore;
import domain.card.CardType;
import domain.gamer.Dealer;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackBetCalculatorTest {
    @Nested
    class playerBetTest {
        Map<String, Integer> betMap;
        private BlackJackBetCalculator calculator;

        @BeforeEach
        void init() {
            betMap = new HashMap<>();
            betMap.put("가이온", 1000);
            calculator = new BlackJackBetCalculator(betMap);
        }

        @DisplayName("플레이어가 딜러를 상대로 승리한 경우 배팅한 금액만큼 얻는다")
        @Test
        void winningTest() {
            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온", winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup losingCardGroup = new CardGroup();
            Dealer dealer = new Dealer(losingCardGroup);

            int winningBet = calculator.determineBettingAmount(dealer, winningPlayer);

            assertThat(winningBet).isEqualTo(1000);
        }

        @DisplayName("플레이어가 딜러를 상대로 패배한 경우 배팅한 금액만큼 잃는다")
        @Test
        void losingTest() {
            CardGroup losingCardGroup = new CardGroup();
            Player losingPlayer = new Player("가이온", losingCardGroup);

            CardGroup winningCardGroup = new CardGroup();
            Dealer dealer = new Dealer(winningCardGroup);
            dealer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            int winningBet = calculator.determineBettingAmount(dealer, losingPlayer);

            assertThat(winningBet).isEqualTo(-1000);
        }

        @DisplayName("플레이어가 딜러를 상대로 무승부한 경우 배팅한 금액을 그래돌 돌려받는다")
        @Test
        void drawingTest() {
            CardGroup drawingPlayerCardGroup = new CardGroup();
            Player drawingPlayer = new Player("가이온", drawingPlayerCardGroup);
            drawingPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup drawingDealerCardGroup = new CardGroup();
            Dealer drawingDealer = new Dealer(drawingDealerCardGroup);
            drawingDealer.receiveCard(new Card(CardType.DIAMOND, CardScore.TEN));

            int drawingBet = calculator.determineBettingAmount(drawingDealer, drawingPlayer);

            assertThat(drawingBet).isEqualTo(0);
        }

        @DisplayName("플레이어가 블랙잭으로 승리한 경우 1.5배를 받는다")
        @Test
        void blackJackWinningTest() {
            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온", winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.ACE));


            CardGroup losingCardGroup = new CardGroup();
            Dealer dealer = new Dealer(losingCardGroup);

            int winningBet = calculator.determineBettingAmount(dealer, winningPlayer);

            assertThat(winningBet).isEqualTo(1500);
        }
    }

    @Nested
    class betResultTest {
        @DisplayName("플레이어가 1000원과 2000원을 베팅하면 딜러는 3000원을 얻는다")
        @Test
        void blackJackDealerTest() {
            Map<String, Integer> map = new HashMap<>();
            map.put("가이온", 1000);
            map.put("가이온1", 2000);
            BlackJackBetCalculator blackJackBetCalculator = new BlackJackBetCalculator(map);

            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온", winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup winningCardGroup1 = new CardGroup();
            Player winningPlayer1 = new Player("가이온1", winningCardGroup1);
            winningPlayer1.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup losingCardGroup = new CardGroup();
            losingCardGroup.addCard(new Card(CardType.CLOVER, CardScore.ACE));
            losingCardGroup.addCard(new Card(CardType.CLOVER, CardScore.JACK));
            Dealer dealer = new Dealer(losingCardGroup);

            int winningBet = blackJackBetCalculator.getDealerBetResult(dealer, List.of(
                    winningPlayer,
                    winningPlayer1));

            assertThat(winningBet).isEqualTo(3000);
        }

        @DisplayName("플레이어가 1000원과 2000원을 베팅하고 딜러가 승리하면 딜러는 3000원을 얻는다")
        @Test
        void blackJackDealerTestTest1() {
            Map<String, Integer> map = new HashMap<>();
            map.put("가이온", 1000);
            map.put("가이온1", 2000);
            BlackJackBetCalculator blackJackBetCalculator = new BlackJackBetCalculator(map);

            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온", winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup winningCardGroup1 = new CardGroup();
            Player winningPlayer1 = new Player("가이온1", winningCardGroup1);
            winningPlayer1.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup losingCardGroup = new CardGroup();
            losingCardGroup.addCard(new Card(CardType.CLOVER, CardScore.ACE));
            losingCardGroup.addCard(new Card(CardType.CLOVER, CardScore.JACK));
            Dealer dealer = new Dealer(losingCardGroup);

            int winningBet = blackJackBetCalculator.getDealerBetResult(dealer, List.of(
                    winningPlayer,
                    winningPlayer1));

            assertThat(winningBet).isEqualTo(3000);
        }

        @DisplayName("플레이어가 1000원과 2000원을 베팅하고 딜러가 패배하면 딜러는 3000원을 잃는다")
        @Test
        void blackJackDealerTestTest2() {
            Map<String, Integer> map = new HashMap<>();
            map.put("가이온", 1000);
            map.put("가이온1", 2000);
            BlackJackBetCalculator blackJackBetCalculator = new BlackJackBetCalculator(map);

            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온", winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup winningCardGroup1 = new CardGroup();
            Player winningPlayer1 = new Player("가이온1", winningCardGroup1);
            winningPlayer1.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));
            winningPlayer1.receiveCard(new Card(CardType.CLOVER, CardScore.FIVE));

            CardGroup losingCardGroup = new CardGroup();
            losingCardGroup.addCard(new Card(CardType.CLOVER, CardScore.ACE));
            Dealer dealer = new Dealer(losingCardGroup);

            int winningBet = blackJackBetCalculator.getDealerBetResult(dealer, List.of(
                    winningPlayer,
                    winningPlayer1));

            assertThat(winningBet).isEqualTo(-3000);
        }

        @DisplayName("플레이어가 1000원 배팅한 플레이어를 이기고 2000원 배팅한 플레이어에게 지면 딜러는 1000원을 잃는다.")
        @Test
        void blackJackDealerTestTest3() {
            Map<String, Integer> map = new HashMap<>();
            map.put("가이온", 1000);
            map.put("가이온1", 2000);
            BlackJackBetCalculator blackJackBetCalculator = new BlackJackBetCalculator(map);

            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온", winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));

            CardGroup losingCardGroup = new CardGroup();
            Player losingPlayer = new Player("가이온1", losingCardGroup);
            losingPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.FIVE));

            CardGroup dealerCardGroup = new CardGroup();
            dealerCardGroup.addCard(new Card(CardType.CLOVER, CardScore.JACK));
            Dealer dealer = new Dealer(dealerCardGroup);

            int winningBet = blackJackBetCalculator.getDealerBetResult(dealer, List.of(
                    winningPlayer,
                    losingPlayer));

            assertThat(winningBet).isEqualTo(1000);
        }

        @DisplayName("플레이어가 1000원 배팅한 플레이어를에게 패배하고 플레이어가 블랙잭이면 1500원을 잃는다")
        @Test
        void blackJackDealerTestTest4() {
            Map<String, Integer> map = new HashMap<>();
            map.put("가이온", 1000);
            BlackJackBetCalculator blackJackBetCalculator = new BlackJackBetCalculator(map);

            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온", winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.ACE));

            CardGroup dealerCardGroup = new CardGroup();
            dealerCardGroup.addCard(new Card(CardType.CLOVER, CardScore.JACK));
            Dealer dealer = new Dealer(dealerCardGroup);

            int winningBet = blackJackBetCalculator.getDealerBetResult(dealer, List.of(
                    winningPlayer));

            assertThat(winningBet).isEqualTo(-1500);
        }

        @DisplayName("플레이어가 1000원 배팅한 플레이어를에게 비기면 돈을 잃지 않는다.")
        @Test
        void blackJackDealerTestTest5() {
            Map<String, Integer> map = new HashMap<>();
            map.put("가이온", 1000);
            BlackJackBetCalculator blackJackBetCalculator = new BlackJackBetCalculator(map);

            CardGroup winningCardGroup = new CardGroup();
            Player winningPlayer = new Player("가이온", winningCardGroup);
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.TEN));
            winningPlayer.receiveCard(new Card(CardType.CLOVER, CardScore.ACE));

            CardGroup dealerCardGroup = new CardGroup();
            dealerCardGroup.addCard(new Card(CardType.CLOVER, CardScore.JACK));
            dealerCardGroup.addCard(new Card(CardType.CLOVER, CardScore.ACE));
            Dealer dealer = new Dealer(dealerCardGroup);

            int winningBet = blackJackBetCalculator.getDealerBetResult(dealer, List.of(
                    winningPlayer));

            assertThat(winningBet).isEqualTo(0);
        }
    }
}
