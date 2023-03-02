package blackjack.domain.user.name;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerNameTest {

    @Nested
    class 플레이어_이름은 {

        @Test
        void _이름을_가진다() {
            //given
            String name = "junho";

            //when
            PlayerName user = new PlayerName(name);

            //then
            Assertions.assertThat(user.getName())
                    .isEqualTo(name);
        }

        @ParameterizedTest(name = "{1}")
        @CsvSource(value = {"junho주노너무긴이름입니다:10자 이상의 이름을 가지면 예외", "'     ': 공백만 가지면 예외",
                "딜러:딜러 라는 이름을 가지면 예외"}, delimiter = ':')
        void _10자_이상의_이름을_가지면_예외(final String name, final String message) {
            //then
            Assertions.assertThatThrownBy(() -> new PlayerName(name))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
