package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Nested
    @DisplayName("드로우 가능 여부를 확인할 수 있다.")
    class CanDraw {

        @Test
        @DisplayName("가능하다면 true를 반환한다.")
        void canDraw() {
            final Player player = Player.newInstance("user", Arrays.asList(Card.of(SPADE, FIVE), Card.of(SPADE, KING)));
            assertTrue(player.canDraw());
        }

        @Test
        @DisplayName("불가능하다면 false를 반환한다.")
        void cannotDraw() {
            final Player player = Player.newInstance("user", Arrays.asList(Card.of(SPADE, A), Card.of(SPADE, KING)));
            assertFalse(player.canDraw());
        }
    }

    @Test
    @DisplayName("카드를 받을 수 있다.")
    void draw() {
        final Player player = Player.newInstance("user", new ArrayList<>());
        final Card card = Card.cards().get(0);
        player.draw(card);
        assertThat(player.getCards()).containsExactly(card);
    }

    @Test
    @DisplayName("카드를 받은 후, 버스트가 되면 종료 상태가 된다.")
    void drawBust() {
        final List<Card> cards = new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, KING)));
        final Player player = Player.newInstance("user", cards);
        player.draw(Card.of(SPADE, FIVE));
        assertFalse(player.canDraw());
    }

    @Test
    @DisplayName("턴을 종료할 수 있다.")
    void endTurn() {
        final Player player = Player.newInstance("user", new ArrayList<>());
        player.stay();
        assertFalse(player.canDraw());
    }
}
