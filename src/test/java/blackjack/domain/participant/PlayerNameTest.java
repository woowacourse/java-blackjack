package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {

    @Nested
    @DisplayName("플레이어 이름 검증 테스트")
    class NameTest {

        @ParameterizedTest
        @DisplayName("이름이 글자라면 생성된다")
        @ValueSource(strings = {"hula", "sana", "pppk", "iiif", "wilson", "hans", "duri", "hoddeok"})
        void generateByLetter(String value) {
            assertThatCode(() -> new Player(new PlayerName(value)))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @DisplayName("이름이 글자가 아니라면 예외를 던진다")
        @ValueSource(strings = {"hula12", "사na)", "피케2", "이-프"})
        void exceptionWithNonLetter(String value) {
            assertThatThrownBy(() -> new Player(new PlayerName(value)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
