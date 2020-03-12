package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinOrLoseTest {
	@ParameterizedTest
	@MethodSource("generateScoreInterval")
	public void getInstanceTest(int scoreInterval, WinOrLose expected) {
		assertThat(WinOrLose.of(scoreInterval)).isEqualTo(expected);
	}

	static Stream<Arguments> generateScoreInterval() {
		return Stream.of(
			Arguments.of(1, WinOrLose.WIN),
			Arguments.of(0, WinOrLose.DRAW),
			Arguments.of(-1, WinOrLose.LOSE));
	}
}