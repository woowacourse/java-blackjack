package BlackJack.domain.User;

import BlackJack.domain.Card.*;
import BlackJack.domain.Card.Number;
import BlackJack.domain.Result;
import BlackJack.domain.utils.FixedCardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private CardFactory cardFactory;
    @BeforeEach
    public void setUp(){
        cardFactory = new FixedCardFactory();

    }

    @Test
    @DisplayName("딜러가 소지한 카드가 16 이하면 true를 반환한다.")
    void checkScoreWhenUnder16Test() {
        Dealer dealer = new Dealer(cardFactory);
        assertThat(dealer.checkScore()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 소지한 카드가 16 초과면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        Dealer dealer = new Dealer(cardFactory);
        dealer.addCard(cardFactory);
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
        Dealer dealer = new Dealer(cardFactory);
        dealer.addCard(cardFactory);
        dealer.addCard(cardFactory);

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
       Player player = new Player("test", playerCards);
       Dealer dealer = new Dealer(cardFactory);
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

        Player player = new Player("test", playerCards);
        Dealer dealer = new Dealer(cardFactory);
        dealer.addCard(cardFactory);
        dealer.addCard(cardFactory);
        dealer.addCard(cardFactory);
        Result actual = dealer.compare(player);
        Result expected = Result.WIN;
        assertThat(actual).isEqualTo(expected);
    }


}
