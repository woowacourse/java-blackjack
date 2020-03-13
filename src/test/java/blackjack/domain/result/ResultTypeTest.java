package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.user.hand.Score;

class ResultTypeTest {

	@ParameterizedTest
	@MethodSource("provideDealerAndPlayerScoreWithReturnType")
	void from_DealerScoreAndPlayerScore_ReturnResultType(Score dealerScore, Score playerScore, ResultType expected) {
		assertThat(ResultType.from(dealerScore, playerScore)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideDealerAndPlayerScoreWithReturnType() {
		return Stream.of(
			Arguments.arguments(Score.valueOf(10), Score.valueOf(11), ResultType.WIN),
			Arguments.arguments(Score.valueOf(10), Score.valueOf(10), ResultType.DRAW),
			Arguments.arguments(Score.valueOf(11), Score.valueOf(10), ResultType.LOSE));
	}
}
