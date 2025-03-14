package domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class PlayerTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"a", "abcdeabcde"})
    void 한자리에서_열자리사이의_닉네임으로_된_플레이어를_만들수_있다(String name) {
        //given
        //when
        //then
        assertThatCode(
            () -> new Player(name)
        ).doesNotThrowAnyException();
    }

    @Test
    void 플레이어의_닉네임이_11자_이상이면_예외발생() {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Player("asdfghjjklz"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ",})
    @NullSource
    @EmptySource
    void 플레이어의_닉네임이_공백이면_예외발생(String name) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
