package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @ParameterizedTest
    @ValueSource(ints = {-1,52})
    void 카드박스에서_카드추출시_0부터_52사이의_위치값을_입력하지_않으면_에러발생(int index){
        assertThatThrownBy(
            () -> Deck.get(index)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
