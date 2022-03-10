package BlackJack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    @Test
    @DisplayName("딜러가 소지한 카드가 16 이하면 true를 반환한다.")
    void checkScoreWhenUnder16Test() {
        Cards under16Cards = new Cards(Arrays.asList(new Card(Shape.HEART, Number.JACK)));
        Dealer dealer = new Dealer(under16Cards);
        assertThat(dealer.checkScore()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 소지한 카드가 16 초과면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        Cards over16Cards = new Cards(Arrays.asList(
               new Card(Shape.HEART, Number.JACK),
               new Card(Shape.HEART, Number.TEN)));
        Dealer dealer = new Dealer(over16Cards);
        assertThat(dealer.checkScore()).isEqualTo(false);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 21이 넘으면 플레이어가 패배한다.")
    void PlayerIsLose_WhenOver21() {
         Cards over21Cards = new Cards(Arrays.asList(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.HEART, Number.TEN),
                new Card(Shape.CLOVER, Number.TWO)));
        Player player = new Player("test", over21Cards);
        Dealer dealer = new Dealer(over21Cards);
        Result actual = dealer.compare(player);
        Result expected = Result.LOSE;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 21이하이며, 딜러보다 21에 가까울때 플레이어가 승리한다.")
    void PlayerWinTest_WhenUnder21AndOverThanDealer() {
        Cards playerCards = new Cards(Arrays.asList(
               new Card(Shape.HEART, Number.JACK),
               new Card(Shape.HEART, Number.TEN)));
        Cards dealerCards = new Cards(Arrays.asList(
                       new Card(Shape.HEART, Number.JACK)));
       Player player = new Player("test", playerCards);
       Dealer dealer = new Dealer(dealerCards);
       Result actual = dealer.compare(player);
       Result expected = Result.WIN;
       assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 카드가 21을 넘으면 21이 안넘은 플레이어는 승리한다.")
    void PlayerIsWinTest_WhenUnder21AndDealerOverThan21() {
        Cards playerCards = new Cards(Arrays.asList(
                       new Card(Shape.HEART, Number.JACK),
                       new Card(Shape.HEART, Number.TEN)));
        Cards dealerCards = new Cards(Arrays.asList(
                       new Card(Shape.HEART, Number.JACK),
                new Card(Shape.CLOVER, Number.JACK),
                new Card(Shape.HEART, Number.EIGHT)));
        Player player = new Player("test", playerCards);
        Dealer dealer = new Dealer(dealerCards);
        Result actual = dealer.compare(player);
        Result expected = Result.WIN;
        assertThat(actual).isEqualTo(expected);
    }


}