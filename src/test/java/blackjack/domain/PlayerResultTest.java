package blackjack.domain;

import blackjack.domain.Card.Number;
import blackjack.domain.Card.*;
import blackjack.domain.User.Betting;
import blackjack.domain.User.Dealer;
import blackjack.domain.User.Player;
import blackjack.domain.utils.FixedCardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultTest {
    private CardFactory cardFactory;

    @BeforeEach
    public void setUp() {
        cardFactory = new FixedCardFactory();
    }

    @Test
    @DisplayName("플레이어의 카드 합이 21이 넘으면 플레이어가 패배한다.")
    void PlayerIsLose_WhenOver21() {
        Cards over21Cards = new Cards(Arrays.asList(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.HEART, Number.TEN),
                new Card(Shape.CLOVER, Number.TWO)));
        Cards cards = new Cards(List.of(new Card(Shape.DIAMOND, Number.TEN), new Card(Shape.DIAMOND, Number.JACK)));
        Player player = new Player("test", Betting.from(1000), over21Cards);
        Dealer dealer = new Dealer(cards);
        dealer.hit(cardFactory);
        dealer.hit(cardFactory);

        PlayerResult actual = PlayerResult.valueOf(dealer, player);
        PlayerResult expected = PlayerResult.LOSE;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 21이하이며, 딜러보다 21에 가까울때 플레이어가 승리한다.")
    void PlayerWinTest_WhenUnder21AndOverThanDealer() {
        Cards playerCards = new Cards(Arrays.asList(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.HEART, Number.TEN)));
        Cards cards = new Cards(List.of(new Card(Shape.DIAMOND, Number.TEN), new Card(Shape.DIAMOND, Number.TWO)));
        Player player = new Player("test", Betting.from(1000), playerCards);
        Dealer dealer = new Dealer(cards);
        PlayerResult actual = PlayerResult.valueOf(dealer, player);
        PlayerResult expected = PlayerResult.WIN;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 카드가 21을 넘으면 21이 안넘은 플레이어는 승리한다.")
    void PlayerIsWinTest_WhenUnder21AndDealerOverThan21() {
        Cards playerCards = new Cards(Arrays.asList(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.HEART, Number.TEN)));
        Cards dealerCards = new Cards(List.of(new Card(Shape.DIAMOND, Number.TEN), new Card(Shape.DIAMOND, Number.JACK),
                new Card(Shape.CLOVER, Number.TEN)));
        Player player = new Player("test", Betting.from(1000), playerCards);
        Dealer dealer = new Dealer(dealerCards);
        dealer.hit(cardFactory);
        dealer.hit(cardFactory);
        PlayerResult actual = PlayerResult.valueOf(dealer, player);
        PlayerResult expected = PlayerResult.WIN;
        assertThat(actual).isEqualTo(expected);
    }
}
