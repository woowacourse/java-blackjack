package blackJack.domain.User;

import blackJack.domain.Card.*;
import blackJack.domain.Card.Number;
import blackJack.domain.PlayerResult;
import blackJack.domain.utils.FixedCardFactory;
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
        assertThat(dealer.canOneMoreCard()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 소지한 카드가 16 초과면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        Dealer dealer = new Dealer(cardFactory);
        dealer.hit(cardFactory);
        assertThat(dealer.canOneMoreCard()).isEqualTo(false);
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
        dealer.hit(cardFactory);
        dealer.hit(cardFactory);

        PlayerResult actual = dealer.compare(player);
        PlayerResult expected = PlayerResult.LOSE;
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
       PlayerResult actual = dealer.compare(player);
       PlayerResult expected = PlayerResult.WIN;
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
        dealer.hit(cardFactory);
        dealer.hit(cardFactory);
        dealer.hit(cardFactory);
        PlayerResult actual = dealer.compare(player);
        PlayerResult expected = PlayerResult.WIN;
        assertThat(actual).isEqualTo(expected);
    }


}
