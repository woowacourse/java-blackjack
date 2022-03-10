package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.human.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
public class NameTest {

    @Test
    public void 참여자이름_객체_생성_통과() {
        assertThat(Name.of("jack").getName())
                .isEqualTo("jack");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ja ck", "jac.k",""})
    public void 참여자이름_객체_생성_실패(String input) {
        assertThatThrownBy(() -> Name.of(input))
                .isInstanceOf(Exception.class)
                .hasMessage("이름 형식에 맞게 입력해야 합니다");
    }
}
