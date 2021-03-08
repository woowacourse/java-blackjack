package blackjack.domain.user;

import blackjack.domain.card.*;
import blackjack.domain.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @DisplayName("Player 객체를 생성한다.")
    @Test
    public void createPlayer() {
        Player player = new Player("amazzi");

        assertThat(player).isInstanceOf(Player.class);
    }

    @DisplayName("카드를 두장 분배받는다.")
    @Test
    public void distributeTwoCards() {
        Player player = new Player("amazzi");
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.NINE),
                new Card(Shape.DIAMOND, Value.JACK)
        )));
        Cards cards = player.cards;

        assertThat(cards.cards()).hasSize(2);
    }

    @DisplayName("카드 합계가 21 이하인지 확인한다. - 카드를 더 받을 수 있다.")
    @Test
    public void isHitTrue() {
        Player player = new Player("amazzi");
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK)
        )));

        assertThat(player.isHit()).isTrue();
    }

    @DisplayName("카드 합계가 21 초과인지 확인한다. - 카드를 더 받을 수 없다.")
    @Test
    public void isHitFalse() {
        Player player = new Player("amazzi");
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK),
                new Card(Shape.CLOVER, Value.QUEEN)
        )));

        assertThat(player.isHit()).isFalse();
    }

    @DisplayName("카드 합계가 16이하인 경우 카드를 한장 추가로 받는다.")
    @Test
    void draw() {
        Deck deck = new Deck();
        Player player = new Player("amazzi");
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        player.draw(deck);
        Cards cards = player.cards;

        assertThat(cards.cards()).hasSize(3);
    }

    @DisplayName("카드 두장을 공개한다.")
    @Test
    void show() {
        Player player = new Player("amazzi");
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Cards cards = player.getCards();

        assertThat(cards.cards()).hasSize(2);
    }

    @DisplayName("딜러에 버스트가 있고 플레이어에 버스트가 없는 경우를 확인한다. - 플레이어 승")
    @Test
    public void decideResultByBustPlayerWin() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(player.decideResultByBust(dealer)).isEqualTo(Result.WIN);
    }

    @DisplayName("딜러에 버스트가 있고 플레이어에도 버스트가 있는 경우를 확인한다. - 무승부")
    @Test
    public void decideResultByBustStandoff() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));

        assertThat(player.decideResultByBust(dealer)).isEqualTo(Result.STANDOFF);
    }

    @DisplayName("딜러에 버스트가 없고 플레이어에 버스트가 있는 경우를 확인한다. - 플레이어 패")
    @Test
    public void decideResultByBustPlayerLose() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        player.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));

        assertThat(player.decideResultByBust(dealer)).isEqualTo(Result.LOSE);
    }
}
