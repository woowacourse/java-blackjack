package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MatchResultTest {
	@ParameterizedTest
	@MethodSource("generateScoreInterval")
	public void getInstanceTest(int playerScore, int dealerScore, int cardSize, MatchResult expected) {
		assertThat(MatchResult.of(playerScore, dealerScore, cardSize)).isEqualTo(expected);
	}

	static Stream<Arguments> generateScoreInterval() {
		return Stream.of(
			Arguments.of(19, 17, 3, MatchResult.WIN),
			Arguments.of(19, 22, 3, MatchResult.WIN),
			Arguments.of(18, 18, 3, MatchResult.DRAW),
			Arguments.of(15, 20, 3, MatchResult.LOSE),
			Arguments.of(22, 16, 3, MatchResult.BUST));
	}
}