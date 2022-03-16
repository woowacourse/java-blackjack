package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    void 카드덱에_중복되지않은_52장이_들어오지_않는_경우_예외발생() {
        final Queue<Card> cards = new ArrayDeque<>();
        assertThatThrownBy(() -> new CardDeck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 덱은 중복되지 않은 52장으로만 생성할 수 있습니다.");
    }
}
