package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackServiceTest {

    private final BlackjackService blackjackService = new BlackjackService();

    @Nested
    class CreatePlayersTest {

        @Nested
        class Success {

            @ParameterizedTest
            @MethodSource("successCases")
            void 게임_참가자_조건이_맞으면_정상_작동_해야한다(List<String> input) {

                // when & then
                blackjackService.createPlayers(input);
            }

            static Stream<Arguments> successCases() {
                return Stream.of(
                    Arguments.of(List.of("jacob")),
                    Arguments.of(List.of("aa aa"))
                );
            }
        }

        @Nested
        class Fail {

            @Test
            void 게임_참가_인원은_2_이상_8_이하가_아니라면_예외를_발생_시켜야_한다() {

                // given
                List<String> input = List.of("jacob");

                // when & then
                assertThatThrownBy(() -> {
                    blackjackService.createPlayers(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 게임 참가자의 수는 2~8명 사이여야 합니다.");
            }

            @Test
            void 입력값에_공백만_입력되면_예외를_발생_시켜야_한다() {

                // given
                List<String> input = List.of("");

                // when & then
                assertThatThrownBy(() -> {
                    blackjackService.createPlayers(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 게임 참가자의 이름은 공백이 될 수 없습니다.");
            }

            @ParameterizedTest
            @MethodSource("provideCases")
            void 게임_참가자_이름의_길이가_2_이상_5_이하가_아니라면_예외를_발생_시켜야_한다(List<String> input) {

                // when & then
                assertThatThrownBy(() -> {
                    blackjackService.createPlayers(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 게임 참가자의 이름은 2~5글자 사이여야 합니다.");
            }

            static Stream<Arguments> provideCases() {
                return Stream.of(
                    Arguments.of(List.of("j")),
                    Arguments.of(List.of("aa aaa"))
                );
            }
        }


    }
}