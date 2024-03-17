package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    Dealer dealer;

    @Test
    @DisplayName("카드의 총합이 16 이하이면 카드를 받을 수 있다.")
    void receiveCardTest() {
        dealer = new Dealer(List.of(
                new Card(Shape.CLOVER, Number.KING),
                new Card(Shape.HEART, Number.SIX)
        ));

        assertTrue(dealer.canReceiveCard());
    }

    @Test
    @DisplayName("블랙잭이면 카드를 받지 않는다.")
    void cantReceiveCardTest_WhenBlackjack() {
        dealer = new Dealer(List.of(
                new Card(Shape.CLOVER, Number.KING),
                new Card(Shape.HEART, Number.ACE)
        ));

        assertFalse(dealer.canReceiveCard());
    }

    @Test
    @DisplayName("카드의 총합이 16을 초과하면 카드를 받을 수 없다.")
    void cantReceiveCardTest() {
        dealer = new Dealer(List.of(
                new Card(Shape.CLOVER, Number.KING),
                new Card(Shape.HEART, Number.SEVEN)
        ));

        assertFalse(dealer.canReceiveCard());
    }

    @Test
    @DisplayName("모든 플레이어와, 딜러 자신에게 2장의 카드를 배분한다.")
    void setUpInitialCardsTest() {
        List<Player> players = List.of(
                new Player("p1"),
                new Player("p2"),
                new Player("p3")
        );
        dealer = new Dealer();

        dealer.setUpInitialCards(players);

        List<Integer> actualCardCount = players.stream()
                .map(player -> player.getCurrentCards().size())
                .toList();

        assertThat(dealer.getCurrentCards().size()).isEqualTo(2);
        assertThat(actualCardCount).containsExactly(2, 2, 2);
    }

    @Test
    @DisplayName("현재 보유하고 있는 카드가 존재하지 않으면 예외가 발생된다.")
    void getCurrentCardExceptionTest() {
        dealer = new Dealer();
        assertThatThrownBy(dealer::getCurrentCards)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 보유하고 있는 카드가 존재하지 않습니다.");
    }
}
