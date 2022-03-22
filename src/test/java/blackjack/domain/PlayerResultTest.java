package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.domain.user.Money;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static blackjack.fixture.CardBundleFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultTest {

    @Test
    @DisplayName("플레이어의 카드 합이 21이 넘으면 플레이어가 패배한다.")
    void PlayerIsLose_WhenOver21() {

        Player player = new Player("test", Money.from(1000), _21_OVER_CARDS);
        Dealer dealer = new Dealer(_16_CARDS);

        PlayerResult actual = PlayerResult.valueOf(dealer, player);
        PlayerResult expected = PlayerResult.LOSE;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 21이하이며, 딜러보다 21에 가까울때 플레이어가 승리한다.")
    void PlayerWinTest_WhenUnder21AndOverThanDealer() {
        Cards playerCards = new Cards(Arrays.asList(
                Card.valueOf(Shape.HEART, Number.JACK),
                Card.valueOf(Shape.HEART, Number.TEN)));
        Cards cards = new Cards(List.of(Card.valueOf(Shape.DIAMOND, Number.TEN), Card.valueOf(Shape.DIAMOND, Number.TWO)));
        Player player = new Player("test", Money.from(1000), playerCards);
        Dealer dealer = new Dealer(cards);
        PlayerResult actual = PlayerResult.valueOf(dealer, player);
        PlayerResult expected = PlayerResult.WIN;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 카드가 21을 넘으면 21이 안넘은 플레이어는 승리한다.")
    void PlayerIsWinTest_WhenUnder21AndDealerOverThan21() {
        Player player = new Player("test", Money.from(1000), _16_CARDS);
        Dealer dealer = new Dealer(_21_OVER_CARDS);
        PlayerResult actual = PlayerResult.valueOf(dealer, player);
        PlayerResult expected = PlayerResult.WIN;
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    @DisplayName("플레이어가 21이고 딜러가 블랙잭으로 점수가 같다면 플레이어가 진다.")
    void isSameScoreWithBlackjackTest() {
        Cards playerCards = new Cards(List.of(Card.valueOf(Shape.CLOVER, Number.JACK), Card.valueOf(Shape.CLOVER, Number.TEN), Card.valueOf(Shape.CLOVER, Number.ACE)));
        Player player = new Player("test", Money.from(1000), playerCards);
        Dealer dealer = new Dealer(JACK_ACE_BLACKJACK);
        PlayerResult actual = PlayerResult.valueOf(dealer, player);
        assertThat(actual).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어가 둘 다 블랙잭이면 무승부이다.")
    void isAllBlackjackTest() {
        Player player = new Player("test", Money.from(1000), JACK_ACE_BLACKJACK);
        Dealer dealer = new Dealer(JACK_ACE_BLACKJACK);
        PlayerResult actual = PlayerResult.valueOf(dealer, player);
        assertThat(actual).isEqualTo(PlayerResult.DRAW);
    }
}
