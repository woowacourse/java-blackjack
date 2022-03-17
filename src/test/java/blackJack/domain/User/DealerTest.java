package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Cards;
import blackJack.domain.Card.Denomination;
import blackJack.domain.Card.Suit;
import blackJack.domain.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private Dealer dealer = new Dealer();
    Player player = new Player("test");

    @Test
    @DisplayName("딜러가 소지한 카드가 16 이하면 true를 반환한다.")
    void checkScoreWhenUnder16Test() {
        dealer.cards.add(new Card(Suit.HEART, Denomination.JACK));
        assertThat(dealer.isPossibleToAdd()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 소지한 카드가 16 초과면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        dealer.cards.add(new Card(Suit.HEART, Denomination.JACK));
        dealer.cards.add(new Card(Suit.HEART, Denomination.TEN));
        assertThat(dealer.isPossibleToAdd()).isEqualTo(false);
    }

    @Test
    @DisplayName("딜러가 블랙잭인지 확인한다.")
    void checkBlackJack() {
        dealer.cards.add(new Card(Suit.HEART, Denomination.TEN));
        dealer.cards.add(new Card(Suit.HEART, Denomination.ACE));
        assertThat(dealer.isBlackJack()).isEqualTo(true);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 21이 넘으면 플레이어가 패배한다.")
    void PlayerIsLose_WhenOver21() {
      player.cards = new Cards(Arrays.asList(
            new Card(Suit.HEART, Denomination.JACK),
            new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.CLOVER, Denomination.TWO)));
        Result actual = Result.judge(dealer,player);
        Result expected = Result.LOSE;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 21이하이며, 딜러보다 21에 가까울때 플레이어가 승리한다.")
    void PlayerWinTest_WhenUnder21AndOverThanDealer() {
        player.cards.add(new Card(Suit.HEART, Denomination.JACK));
        player.cards.add(new Card(Suit.HEART, Denomination.TEN));
        dealer.cards.add(new Card(Suit.HEART, Denomination.JACK));

       Result actual = Result.judge(dealer,player);
       Result expected = Result.WIN;
       assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 카드가 21을 넘으면 21이 안넘은 플레이어는 승리한다.")
    void PlayerIsWinTest_WhenUnder21AndDealerOverThan21() {
        player.cards.add(new Card(Suit.HEART, Denomination.JACK));
        player.cards.add(new Card(Suit.HEART, Denomination.TEN));
        dealer.cards.add(new Card(Suit.HEART, Denomination.JACK));
        dealer.cards.add(new Card(Suit.HEART, Denomination.EIGHT));
        dealer.cards.add(new Card(Suit.CLOVER, Denomination.EIGHT));

        Result actual = Result.judge(dealer,player);
        Result expected = Result.WIN;
        assertThat(actual).isEqualTo(expected);
    }


}