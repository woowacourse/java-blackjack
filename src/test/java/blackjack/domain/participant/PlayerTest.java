package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("플레이어의 이름에 null이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByNull(String input) {
        assertThatThrownBy(() -> new Player(input, true, new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("플레이어의 이름은 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어의 이름에 공백이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByEmpty(String input) {
        assertThatThrownBy(() -> new Player(input, true, new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 공백이 들어올 수 없습니다.");
    }

    @Nested
    @DisplayName("드로우 가능 여부를 확인할 수 있다.")
    class CanDraw {

        @Test
        @DisplayName("가능한 경우 true를 반환한다.")
        void canDraw() {
            final Player player = new Player("user", true, new ArrayList<>());
            assertTrue(player.canDraw());
        }

        @Test
        @DisplayName("불가능한 경우 false를 반환한다.")
        void cannotDraw() {
            final Player player = new Player("user", false, new ArrayList<>());
            assertFalse(player.canDraw());
        }
    }

    @Test
    @DisplayName("드로우가 불가능한데, 카드를 받으려 하면 예외를 발생시킨다.")
    void drawException() {
        final Player player = new Player("user", false, new ArrayList<>());
        assertThatThrownBy(() -> player.draw(Card.cards().get(0)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료되었으면 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("카드를 받을 수 있다.")
    void draw() {
        final Player player = new Player("user", true, new ArrayList<>());
        final Card card = Card.cards().get(0);
        player.draw(card);
        assertThat(player.getCards()).containsExactly(card);
    }

    @Test
    @DisplayName("카드를 받은 후, 버스타가 되면 종료 상태가 된다.")
    void drawBust() {
        final List<Card> cards = new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, KING)));
        final Player player = new Player("user", true, cards);
        player.draw(Card.of(SPADE, FIVE));
        assertFalse(player.canDraw());
    }

    @Test
    @DisplayName("턴을 종료할 수 있다.")
    void endTurn() {
        final Player player = new Player("user", false, new ArrayList<>());
        player.endTurn();
        assertFalse(player.canDraw());
    }

    @Test
    @DisplayName("턴이 종료되지 않은 경우에 카드의 합을 계산하려하면 예외를 발생시킨다.")
    void calculateResultScoreExceptionByNotEndTurn() {
        final Player player = new Player("user", true, new ArrayList<>());
        assertThatThrownBy(player::calculateResultScore)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
    }
}
