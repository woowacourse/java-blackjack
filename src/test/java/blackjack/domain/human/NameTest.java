package blackjack.domain.human;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @Test
    @DisplayName("플레이어이름 객체 생성 성공 테스트")
    public void createNameTest() {
        assertThat(Name.of("jack").getName())
                .isEqualTo("jack");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ja ck", "jac.k",""})
    @DisplayName("플레이어 이름 객체 생성 실패 테스트")
    public void exceptionNameTest(String input) {
        assertThatThrownBy(() -> Name.of(input))
                .isInstanceOf(Exception.class)
                .hasMessage("[ERROR] 입력 형식에 맞춰 입력해주세요.");
    }
}
