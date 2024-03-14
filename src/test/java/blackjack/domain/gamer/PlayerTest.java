package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    Player player;

    @Test
    @DisplayName("이름이 같으면 같은 플레이어로 판단한다.")
    void equalsTest() {
        Player player1 = new Player("name");
        Player player2 = new Player("name");

        assertThat(player1).isEqualTo(player2);
    }

    @Test
    @DisplayName("카드의 총합이 21 이하이면 카드를 받을 수 있다.")
    void receiveCardTest() {
        player = createTestPlayer(List.of(
                new Card(Shape.CLOVER, Number.KING),
                new Card(Shape.HEART, Number.FIVE)
        ));

        assertTrue(player.canReceiveCard());
    }

    @Test
    @DisplayName("블랙잭이면 카드를 받지 않는다.")
    void cantReceiveCardTest_WhenBlackjack() {
        player = createTestPlayer(List.of(
                new Card(Shape.CLOVER, Number.KING),
                new Card(Shape.HEART, Number.ACE)
        ));

        assertFalse(player.canReceiveCard());
    }

    @Test
    @DisplayName("카드의 총합이 21을 초과하면 카드를 받을 수 없다.")
    void cantReceiveCardTest() {
        player = createTestPlayer(List.of(
                new Card(Shape.CLOVER, Number.KING),
                new Card(Shape.HEART, Number.FIVE),
                new Card(Shape.HEART, Number.SEVEN)
        ));

        assertFalse(player.canReceiveCard());
    }

    private Player createTestPlayer(List<Card> cards) {
        return new Player("test", cards);
    }
}
