package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    private Cards cards;

    @BeforeEach
    void setup() {
        cards = new Cards(List.of(new Card(SPADES, KING), new Card(SPADES, FIVE)));
    }

    @Test
    void 플레이어의_이름이_null인_경우_예외발생() {
        assertThatThrownBy(() -> new Player(null, 1000, cards))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름은 null이 들어올 수 없습니다.");
    }


    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    void 배팅금액이_0이하인_경우_예외발생(final int betMoney) {
        assertThatThrownBy(() -> new Player(new Name("name"), betMoney, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅금액은 0이하의 값이 들어올 수 없습니다.");
    }

    @Test
    void 게임상태가_null인_경우_예외발생() {
        assertThatThrownBy(() -> new Player(new Name("name"), 1000, (Cards) null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("cards는 null이 들어올 수 없습니다.");
    }

    @Test
    void 카드를_받아_유저_생성() {
        final Cards cards = new Cards(List.of(new Card(SPADES, KING), new Card(SPADES, FIVE)));
        final Player player = new Player(new Name("name"), 1000, cards);

        assertThat(player).isInstanceOf(Player.class);
    }
}
