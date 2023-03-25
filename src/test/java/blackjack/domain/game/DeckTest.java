package blackjack.domain.game;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @Test
    void 생성시_null_이면_예외() {
        assertThatThrownBy(() -> new Deck(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드에 null 이 들어왔습니다");
    }

}
