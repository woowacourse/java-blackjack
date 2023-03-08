package blackjack.domain.player;

import static blackjack.domain.player.Name.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class NameTest {

    @ParameterizedTest(name = "이름이 null, 빈값, 5자를 초과하는 경우 예외를 던진다. 입력: \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = "우아한형제들")
    void 이름이_null_빈값_5자를_초과하는_경우_예외를_던진다(final String name) {
        assertThatThrownBy(() -> from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 1자 이상, 5자 이하여아 합니다. 입력값:" + name);
    }

    @ParameterizedTest(name = "올바른 길이의 이름을 입력받으면 정상 생성된다. 입력: \"{0}\"")
    @ValueSource(strings = {"팀", "!9 j5", "huchu"})
    void 올바른_길이의_이름을_입력받으면_정상적으로_생성된다(final String name) {
        assertThatNoException().isThrownBy(() -> from(name));
    }

    @Test
    void 갬블러의_이름을_생성할_때_딜러의_이름을_입력받으면_예외를_던진다() {
        assertThatThrownBy(() -> from("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 '딜러'일 수 없습니다.");
    }

    @Test
    void 딜러의_이름을_반환한다() {
        final Name name = Name.createDealerName();

        assertThat(name.getValue()).isEqualTo("딜러");
    }
}
