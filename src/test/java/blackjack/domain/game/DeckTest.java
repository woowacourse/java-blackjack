package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

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
                .hasMessage("카드 숫자가 52장이 아닙니다");
    }

}
