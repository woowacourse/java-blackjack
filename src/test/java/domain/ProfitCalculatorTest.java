package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardSuit;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {
    private ProfitCalculator profitCalculator;

    @BeforeEach
    void setUp() {
        profitCalculator = new ProfitCalculator();
    }

    @Test
    @DisplayName("처음 두 장의 카드 합이 21일 경우(블랙잭), 베팅 금액의 1.5 배를 딜러에게 받는다.")
    void blackjackTest() {
        List<Card> cards1 = new ArrayList<>(List.of(
                new Card(CardScore.JACK, CardSuit.CLUB),
                new Card(CardScore.FIVE, CardSuit.SPADE)
        ));
        Dealer dealer = new Dealer(new Cards(cards1));

        List<Card> cards2 = new ArrayList<>(List.of(
                new Card(CardScore.ACE, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        ));
        Player player = new Player(new Cards(cards2), "pobi", new Money(1000));
        int finalProfit = profitCalculator.calculate(player, dealer, player.getMoney());

        assertThat(player.isBlackjack()).isTrue();
        assertThat(finalProfit).isEqualTo(1500);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우, 플레이어는 베팅한 금액을 돌려받는다.")
    void allBlackjackTest() {
        List<Card> cards1 = new ArrayList<>(List.of(
                new Card(CardScore.ACE, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        ));
        Dealer dealer = new Dealer(new Cards(cards1));

        List<Card> cards = new ArrayList<>(List.of(
                new Card(CardScore.ACE, CardSuit.HEART),
                new Card(CardScore.KING, CardSuit.CLUB)
        ));
        Player player = new Player(new Cards(cards), "pobi", new Money(1000));
        int finalProfit = profitCalculator.calculate(player, dealer, player.getMoney());

        assertThat(dealer.isBlackjack()).isTrue();
        assertThat(player.isBlackjack()).isTrue();
        assertThat(finalProfit).isEqualTo(0);
    }

    @Test
    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우(버스트), 배팅 금액을 모두 잃는다.")
    void playerBurstTest() {
        List<Card> cards1 = new ArrayList<>(List.of(
                new Card(CardScore.JACK, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART),
                new Card(CardScore.FIVE, CardSuit.SPADE)
        ));
        Dealer dealer = new Dealer(new Cards(cards1));

        List<Card> cards2 = new ArrayList<>(List.of(
                new Card(CardScore.KING, CardSuit.CLUB),
                new Card(CardScore.JACK, CardSuit.SPADE)
        ));
        Player player = new Player(new Cards(cards2), "pobi", new Money(1000));
        player.addCard(new Card(CardScore.FIVE, CardSuit.HEART));
        int finalProfit = profitCalculator.calculate(player, dealer, player.getMoney());

        assertThat(player.isBust()).isTrue();
        assertThat(finalProfit).isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러가 21을 초과할 겨우(버스트), 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.")
    void dealerBurstTest() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(CardScore.JACK, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART),
                new Card(CardScore.FIVE, CardSuit.SPADE)
        ));
        Dealer dealer = new Dealer(new Cards(cards));

        List<Card> player1Cards = new ArrayList<>(
                List.of(
                        new Card(CardScore.TWO, CardSuit.CLUB),
                        new Card(CardScore.THREE, CardSuit.CLUB)
                ));
        Player player1 = new Player(new Cards(player1Cards), "pobi", new Money(1000));
        int finalProfit1 = profitCalculator.calculate(player1, dealer, player1.getMoney());

        List<Card> player2Cards = new ArrayList<>(
                List.of(
                        new Card(CardScore.TWO, CardSuit.HEART),
                        new Card(CardScore.THREE, CardSuit.HEART)
                ));
        Player player2 = new Player(new Cards(player2Cards), "woni", new Money(2000));
        int finalProfit2 = profitCalculator.calculate(player2, dealer, player2.getMoney());

        assertThat(dealer.isBust()).isTrue();
        assertThat(finalProfit1).isEqualTo(1000);
        assertThat(finalProfit2).isEqualTo(2000);
    }
}
