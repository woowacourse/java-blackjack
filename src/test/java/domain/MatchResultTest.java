package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 *
 *   @author ParkDooWon
 */
public class MatchResultTest {
	private static Stream<Arguments> matchInput() {
		return Stream.of(
			Arguments.of(true, false, true, MatchResult.BLACKJACK_WIN),
			Arguments.of(true, false, false, MatchResult.WIN),
			Arguments.of(false, true, false, MatchResult.PUSH),
			Arguments.of(false, false, false, MatchResult.DEFEAT)
		);
	}

	@ParameterizedTest
	@MethodSource("matchInput")
	@DisplayName("적절한 매치 결과가 반환되는지 테스트")
	void getMatchResultTest(boolean isWin, boolean isPush, boolean isBlackjack, MatchResult matchResult) {
		assertThat(MatchResult.getMatchResult(isWin, isPush, isBlackjack)).isEqualTo(matchResult);
	}
}
