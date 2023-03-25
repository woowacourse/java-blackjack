package blackjack.domain.participants;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
class NameTest {

    @Test
    void 생성시_null이면_예외() {
        assertThatThrownBy(() -> new User(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름을 입력하지 않았습니다");
    }

    @Test
    void 길이가_100글자_초과시_에러() {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 100)
                .forEach(stringBuilder::append);

        assertThatThrownBy(() -> new User(stringBuilder.toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 100글자를 초과했습니다");
    }

}
