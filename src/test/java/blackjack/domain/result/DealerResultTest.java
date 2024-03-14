package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.player.Name;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerResultTest {
    private Name dealerName;

    @BeforeEach
    void setUp() {
        dealerName = new Name("딜러");
    }

    @Test
    @DisplayName("딜러 이름과 게임 플레이어 결과 리스트를 통해 딜러 결과를 생성한다.")
    void create_with_gamePlayerResultList() {
        Name name = new Name("초롱");
        ResultStatus resultStatus = ResultStatus.DRAW;
        GamePlayerResult gamePlayerResult = new GamePlayerResult(name, resultStatus);

        assertThatCode(() -> {
            var sut = DealerResult.of(new Name("딜러"), List.of(gamePlayerResult));
            assertThat(sut.getCountWithResultStatus(ResultStatus.DRAW)).isEqualTo(1);
        }).doesNotThrowAnyException();
    }

    private static Stream<Arguments> resultStatusMaskingParam() {
        return Arrays.stream(ResultStatus.values())
                     .map(resultStatus ->
                             Arguments.of(resultStatus, resultStatus.reverse())
                     );
    }

    @ParameterizedTest(name = "플레이어 결과 {0} 은 딜러의 {1}가 된다.")
    @MethodSource("resultStatusMaskingParam")
    @DisplayName("게임 플레이어의 결과와 딜러의 결과는 반대이다.")
    void reverse_of_gamePlayerResult(final ResultStatus resultStatus, final ResultStatus reverseStatus) {
        final Name name = new Name("초롱");
        final GamePlayerResult gamePlayerResult = new GamePlayerResult(name, resultStatus);
        final var sut = DealerResult.of(dealerName, List.of(gamePlayerResult));

        final var result = sut.getCountWithResultStatus(reverseStatus);

        assertThat(result).isEqualTo(1);
    }
}
