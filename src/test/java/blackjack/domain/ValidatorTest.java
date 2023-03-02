package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ValidatorTest {
    static Validator validator = new Validator();

    private static Stream<Arguments> provideFailPlayerNames() {
        return Stream.of(
                Arguments.of(List.of("a", "b", "c", "d", "e", "f", "g", "h")),
                Arguments.of(List.of()));
    }

    private static Stream<Arguments> provideSuccessPlayerNames() {
        return Stream.of(Arguments.of(List.of("a")),
                Arguments.of(List.of("a", "b", "c", "d", "e", "f", "g")));
    }

    @DisplayName("player 수 Fail 테스트")
    @ParameterizedTest
    @MethodSource("provideFailPlayerNames")
    void playerCountFail(List<String> input) {
        assertThatThrownBy(() -> validator.validatePlayerNames(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("player 수 성공 테스트")
    @ParameterizedTest
    @MethodSource("provideSuccessPlayerNames")
    void playerCountSuccess(List<String> input) {
        assertThatNoException().isThrownBy(() -> validator.validatePlayerNames(input));
    }

    @Test
    @DisplayName("player 이름 중복 테스트")
    void playerNameDuplicate(){
        List<String> playerNames = List.of("io", "io");
        assertThatThrownBy(() -> validator.validatePlayerNames(playerNames)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("player 의사 유효성 검증")
    @ValueSource(strings = {"y", "n"})
    void playerIntentionSuccess(String input){
        assertThatNoException().isThrownBy(()-> validator.validatePlayerIntention(input));
    }

    @ParameterizedTest
    @DisplayName("player 의사 유효성 검증")
    @ValueSource(strings = {"", "N", "IOIO"})
    void playerIntentionFail(String input){
        assertThatThrownBy(()-> validator.validatePlayerIntention(input)).isInstanceOf(IllegalArgumentException.class);
    }


}
