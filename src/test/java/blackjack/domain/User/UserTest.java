package blackjack.domain.User;

import blackjack.domain.Card.Card;
import blackjack.domain.Card.Cards;
import blackjack.domain.Card.Number;
import blackjack.domain.Card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("유저가 버스트 났으면 True")
    void isBustTest() {
        Cards cards = new Cards(List.of(new Card(Shape.DIAMOND, Number.TEN), new Card(Shape.DIAMOND, Number.JACK),
                new Card(Shape.CLOVER, Number.JACK)));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어와 딜러의 점수가 같다면 True")
    void isSameScoreTest() {
        Cards cards = new Cards(List.of(new Card(Shape.CLOVER, Number.JACK)));
        Player player = new Player("test", Betting.from(1000), cards);
        Dealer dealer = new Dealer(cards);
        assertThat(player.isSameScoreWithNotBlackJack(dealer)).isTrue();
    }

    @Test
    @DisplayName("버스트가 안날 때, 플레이어가 딜러보다 점수가 높으면 True")
    void isGreaterScoreThanTest() {
        Cards playerCards = new Cards(List.of(new Card(Shape.CLOVER, Number.JACK)));
        Cards dealerCards = new Cards(List.of(new Card(Shape.CLOVER, Number.FOUR)));
        Player player = new Player("test", Betting.from(1000), playerCards);
        Dealer dealer = new Dealer(dealerCards);
        assertThat(player.isGreaterScoreThan(dealer)).isTrue();
    }

    @Test
    @DisplayName("ACE와 10의 조합으로 블랙잭이면 True")
    void isBlackJackTest() {
        Cards cards = new Cards(List.of(new Card(Shape.CLOVER, Number.ACE), new Card(Shape.CLOVER, Number.TEN)));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("블랙잭이 아닌, 카드들의 합이 21이면 블랙잭이 아니다. - False")
    void isNotBlackJackTest() {
        Cards cards = new Cards(List.of(new Card(Shape.CLOVER, Number.TWO), new Card(Shape.CLOVER, Number.TEN),
                new Card(Shape.DIAMOND, Number.NINE)));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isBlackJack()).isFalse();
    }
}
