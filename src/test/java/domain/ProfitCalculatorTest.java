package domain;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
import domain.card.CardSuit;
import domain.card.CardValue;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
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
        Dealer dealer = new Dealer(new ArrayList<>(List.of(
                new Card(CardValue.JACK, CardSuit.CLUB),
                new Card(CardValue.FIVE, CardSuit.SPADE)
        )));

        List<Card> cards = new ArrayList<>(List.of(
                new Card(CardValue.ACE, CardSuit.CLUB),
                new Card(CardValue.KING, CardSuit.HEART)
        ));
        Player player = new Player(cards, "pobi", new Money(1000));
        int finalProfit = profitCalculator.calculate(player, dealer, player.getMoney());

        Assertions.assertThat(player.isBlackjack()).isTrue();
        Assertions.assertThat(finalProfit).isEqualTo(1500);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우, 플레이어는 베팅한 금액을 돌려받는다.")
    void allBlackjackTest() {
        Dealer dealer = new Dealer(new ArrayList<>(List.of(
                new Card(CardValue.ACE, CardSuit.CLUB),
                new Card(CardValue.KING, CardSuit.HEART)
        )));

        List<Card> cards = new ArrayList<>(List.of(
                new Card(CardValue.ACE, CardSuit.HEART),
                new Card(CardValue.KING, CardSuit.CLUB)
        ));
        Player player = new Player(cards, "pobi", new Money(1000));
        int finalProfit = profitCalculator.calculate(player, dealer, player.getMoney());

        Assertions.assertThat(dealer.isBlackjack()).isTrue();
        Assertions.assertThat(player.isBlackjack()).isTrue();
        Assertions.assertThat(finalProfit).isEqualTo(0);
    }

    @Test
    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우(버스트), 배팅 금액을 모두 잃는다.")
    void playerBurstTest() {
        Dealer dealer = new Dealer(new ArrayList<>(List.of(
                new Card(CardValue.JACK, CardSuit.CLUB),
                new Card(CardValue.KING, CardSuit.HEART),
                new Card(CardValue.FIVE, CardSuit.SPADE)
        )));

        List<Card> cards = new ArrayList<>(List.of(
                new Card(CardValue.KING, CardSuit.CLUB),
                new Card(CardValue.JACK, CardSuit.SPADE)
        ));
        Player player = new Player(cards, "pobi", new Money(1000));
        player.addCard(new Card(CardValue.FIVE, CardSuit.HEART));
        int finalProfit = profitCalculator.calculate(player, dealer, player.getMoney());

        Assertions.assertThat(player.isBust()).isTrue();
        Assertions.assertThat(finalProfit).isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러가 21을 초과할 겨우(버스트), 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.")
    void dealerBurstTest() {
        Dealer dealer = new Dealer(new ArrayList<>(List.of(
                new Card(CardValue.JACK, CardSuit.CLUB),
                new Card(CardValue.KING, CardSuit.HEART),
                new Card(CardValue.FIVE, CardSuit.SPADE)
        )));

        List<Card> player1Cards = new ArrayList<>(
                List.of(
                        new Card(CardValue.TWO, CardSuit.CLUB),
                        new Card(CardValue.THREE, CardSuit.CLUB)
                ));
        Player player1 = new Player(player1Cards, "pobi", new Money(1000));
        int finalProfit1 = profitCalculator.calculate(player1, dealer, player1.getMoney());

        List<Card> player2Cards = new ArrayList<>(
                List.of(
                        new Card(CardValue.TWO, CardSuit.HEART),
                        new Card(CardValue.THREE, CardSuit.HEART)
                ));
        Player player2 = new Player(player2Cards, "woni", new Money(2000));
        int finalProfit2 = profitCalculator.calculate(player2, dealer, player2.getMoney());

        Assertions.assertThat(dealer.isBust()).isTrue();
        Assertions.assertThat(finalProfit1).isEqualTo(1000);
        Assertions.assertThat(finalProfit2).isEqualTo(2000);
    }
}
