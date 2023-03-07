package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayDeque;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @Test
    void 생성시_null_이면_예외() {
        assertThatThrownBy(() -> new Deck(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드에 null 이 들어왔습니다");
    }

    @Test
    void 생성시_52장이_아니면_예외() {
        assertThatThrownBy(() -> new Deck(new ArrayDeque<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 숫자는 52장이어야 합니다 현재 :0장");
    }

    @Test
    void 제거_시도를_52번보다_많이_하면_예외() {
        final Deck deck = new ShuffledDeckFactory().generate();
        for (int i = 0; i < 52; i++) {
            deck.popCard();
        }
        assertThatThrownBy(deck::popCard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱에 카드가 없습니다");
    }
}
