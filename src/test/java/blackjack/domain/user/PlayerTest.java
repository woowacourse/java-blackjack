package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("amazzi", 10000);
    }

    @DisplayName("Player 객체를 생성한다.")
    @Test
    public void createPlayer() {
        assertThat(player).isInstanceOf(Player.class);
    }

    @DisplayName("카드를 두장 분배받는다.")
    @Test
    public void drawInitialCards() {
        player.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.NINE),
                new Card(Shape.DIAMOND, Value.JACK)
        )));
        int cardSize = player.cards().getCards().size();

        assertThat(cardSize).isEqualTo(2);
    }

    @DisplayName("카드 합계가 21 이하인지 확인한다. - 카드를 더 받을 수 있다.")
    @Test
    public void isRunningTrue() {
        player.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK)
        )));

        assertThat(player.isRunning()).isTrue();
    }

    @DisplayName("카드 합계가 21 초과인지 확인한다. - 카드를 더 받을 수 없다.")
    @Test
    public void isRunningFalse() {
        player.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK)
        )));
        player.hit(new Cards(Collections.singletonList(
                new Card(Shape.CLOVER, Value.QUEEN))));

        assertThat(player.isRunning()).isFalse();
    }

    @DisplayName("카드 두장을 공개한다.")
    @Test
    void show() {
        player.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Cards cards = player.cards();

        assertThat(cards.getCards()).hasSize(2);
    }
}
