package blackjack.domain.player;

import static blackjack.domain.card.CardNumber.EIGHT;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

public class PlayerTest {

    private List<Card> cards;

    @BeforeEach
    void setup() {
        cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN));
    }

    @Test
    @DisplayName("플레이어의 이름이 null이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByNull() {
        assertThatThrownBy(() -> Player.createNewPlayer("player", cards))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름이 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("플레이어의 이름이 공백이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByBlank(final String input) {
        assertThatThrownBy(() -> Player.createNewPlayer(input, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 공백이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("현재 상태가 종료되었는데 상태를 변경하면 예외가 발생해야 한다.")
    void changeGameStatusExceptionByFinished() {
        final Player player = Player.createNewPlayer("player", cards);
        player.changeStatusFinished();
        assertThatThrownBy(() -> player.changeStatusFinished())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 종료된 게임은 종료요청을 할 수 없습니다.");
    }

    @Test
    @DisplayName("첫번째 카드 정보를 반환할 수 있다.")
    void firstDrawCards() {
        final Player player = Player.createNewPlayer("player", cards);
        assertThat(player.firstDrawCards()).isEqualTo(cards);
    }

    @Test
    @DisplayName("종료상태인데 드로우하면 예외가 발생해야 한다.")
    void drawExceptionByFinishedStatus() {
        final Player player = Player.createNewPlayer("player", cards);
        player.drawCard(Card.of(SPADE, NINE));
        assertThatThrownBy(() -> player.drawCard(Card.of(SPADE, EIGHT)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 종료된 게임은 카드를 더 받을 수 없습니다.");
    }
}
