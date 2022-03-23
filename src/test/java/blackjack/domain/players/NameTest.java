package blackjack.domain.players;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.players.participant.name.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @Test
    @DisplayName("이름 객체 생성 통과여부 테스트")
    public void createTest() {
        assertThat(new Name("jack").get())
                .isEqualTo("jack");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ja ck", "jac.k", "", " "})

    @DisplayName("이름 객체 생성 실패여부 테스트")
    public void setNameFailTest(String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름 형식에 맞게 입력해야 합니다.");
    }
}
