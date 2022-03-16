package blackjack.domain.paticipant;

import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.PlayerRunning;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    private BlackjackGameState runningState;

    @BeforeEach
    void setup() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, FIVE)));
        runningState = new PlayerRunning(cards);
    }

    @Test
    void 플레이어의_이름이_null인_경우_예외발생() {
        assertThatThrownBy(() -> new Player(null, 1000, runningState))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름은 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @EmptySource
    void 플레이어의_이름이_공백인_경우_예외발생(final String name) {
        assertThatThrownBy(() -> new Player(name, 1000, runningState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    void 배팅금액이_0이하인_경우_예외발생(final int betMoney) {
        assertThatThrownBy(() -> new Player("name", betMoney, runningState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅금액은 0이하의 값이 들어올 수 없습니다.");
    }

    @Test
    void 게임상태가_null인_경우_예외발생() {
        assertThatThrownBy(() -> new Player("name", 1000, (BlackjackGameState) null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("게임 상태는 null이 들어올 수 없습니다.");
    }

    @Test
    void 카드를_받아_유저_생성() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, FIVE)));
        final Player player = new Player("name", 1000, cards);

        assertThat(player).isInstanceOf(Player.class);
    }
}
