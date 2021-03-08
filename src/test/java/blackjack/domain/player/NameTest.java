package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @DisplayName("Name 객체가 잘 생성되는지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "jason"})
    void 이름_생성_및_비교(String value) {
        //given, when
        Name name = new Name(value);

        //then
        assertThat(name.getName()).isEqualTo(value);
    }

    @DisplayName("Name 객체 생성 에러 테스트")
    @Test
    void 이름을_입력하지_않았을때_에러_발생() {
        assertThatThrownBy(() -> {
            new Name("");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 이름을 입력해야 합니다.");
    }

}