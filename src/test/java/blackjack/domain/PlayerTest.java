package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @DisplayName("User 객체를 생성한다.")
    @Test
    public void createUser() {
        Player player = new Player("amazzi");

        assertThat(player).isInstanceOf(Player.class);
    }

    @DisplayName("카드를 두장 분배받는다.")
    @Test
    public void distributeTwoCards() {
        Player player = new Player("amazzi");
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.NINE),
                new Card(Shape.DIAMOND, Value.JACK)
        )));
        Cards cards = player.cards;

        assertThat(cards.cards().size()).isEqualTo(2);
    }

    @DisplayName("카드 합계가 21 이하인지 확인한다. - 카드를 더 받을 수 있다.")
    @Test
    public void isDrawableTrue() {
        Player player = new Player("amazzi");
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK)
        )));

        assertThat(player.isAbleToHit()).isTrue();
    }

    @DisplayName("카드 합계가 21 초과인지 확인한다. - 카드를 더 받을 수 없다.")
    @Test
    public void isDrawableFalse() {
        Player player = new Player("amazzi");
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK),
                new Card(Shape.CLOVER, Value.QUEEN)
        )));

        assertThat(player.isAbleToHit()).isFalse();
    }

    @DisplayName("카드 합계가 16이하인 경우 카드를 한장 추가로 받는다.")
    @Test
    void draw() {
        Player player = new Player("amazzi");
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        player.hit();
        Cards cards = player.cards;
        assertThat(cards.cards().size()).isEqualTo(3);
    }

    @DisplayName("카드 두장을 공개한다.")
    @Test
    void show() {
        Player player = new Player("amazzi");
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Cards cards = player.showCards();
        assertThat(cards.cards().size()).isEqualTo(2);
    }

    @DisplayName("플레이어의 결과를 산출한다.")
    @Test
    public void decideBustUserLose() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.QUEEN)
        )));
        assertThat(player.produceResult(dealer)).isEqualTo(Result.LOSE);
    }
}
