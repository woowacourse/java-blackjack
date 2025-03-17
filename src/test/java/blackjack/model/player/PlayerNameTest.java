package blackjack.model.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerNameTest {

    @ValueSource(strings = {"", "     "})
    @ParameterizedTest
    void 참가자의_이름이_공백인_경우_예외가_발생한다(String name) {
        // given

        // when & then
        assertThatThrownBy(() -> new PlayerName(name));
    }

    @Test
    void 참가자의_이름이_2글자_이상_5글자가_아니면_예외가_발생한다() {
        // given

        // when & then
        assertThatThrownBy(() -> new PlayerName("한스한스한스"));
    }
}
