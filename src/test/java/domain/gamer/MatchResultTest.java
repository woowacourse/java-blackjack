package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MatchResultTest {
	@ParameterizedTest
	@MethodSource("generateScoreInterval")
	public void getInstanceTest(int scoreInterval, MatchResult expected) {
		assertThat(MatchResult.of(scoreInterval)).isEqualTo(expected);
	}

	static Stream<Arguments> generateScoreInterval() {
		return Stream.of(
			Arguments.of(1, MatchResult.WIN),
			Arguments.of(0, MatchResult.DRAW),
			Arguments.of(-1, MatchResult.LOSE));
	}
}